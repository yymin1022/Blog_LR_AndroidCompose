package com.yong.blog.detail.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yong.blog.R
import com.yong.blog.common.exception.PostException
import com.yong.blog.common.ui.BlogUiStatus
import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.repository.PostDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64

sealed class MarkdownElement {
    data class Image(val srcID: String): MarkdownElement()
    data class Text(val markdownContent: String): MarkdownElement()
}

data class DetailUiState(
    val appBarTitle: Int = R.string.detail_appbar_title,
    val postData: PostData? = null,
    val postImageMap: Map<String, Bitmap?> = emptyMap(),
    val postMarkdownContent: List<MarkdownElement> = emptyList(),
    val uiStatus: BlogUiStatus = BlogUiStatus.UI_STATUS_NORMAL
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PostDetailRepository
): ViewModel() {
    companion object {
        private const val BITMAP_DOWNSCALE_WIDTH = 320
        private const val LOG_TAG = "PostDetail ViewModel"
    }

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    fun getPostData(type: String, id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(uiStatus = BlogUiStatus.UI_STATUS_LOADING) }

            try {
                val postData = repository.getPostData(type, id)
                if(postData == null) throw PostException("PostData [$type] got error")

                val postMarkdownContent = parseMarkdown(postData.postContent)
                _uiState.update {
                    it.copy(
                        postData = postData,
                        postMarkdownContent = postMarkdownContent,
                        uiStatus = BlogUiStatus.UI_STATUS_NORMAL
                    )
                }
            } catch(e: Exception) {
                e.printStackTrace()
                _uiState.update { it.copy(uiStatus = BlogUiStatus.UI_STATUS_ERROR) }
            }
        }
    }

    private fun parseMarkdown(markdownContent: String): List<MarkdownElement> {
        val imageRegex = Regex("""!\[(.*?)]\((.*?)\)|<img.*?src="(.*?)".*?>""")

        var prevIdx = 0
        val parseRes = mutableListOf<MarkdownElement>()
        imageRegex.findAll(markdownContent).forEach { matchRes ->
            val textContent = markdownContent.substring(prevIdx, matchRes.range.first)
            if(textContent.isNotEmpty()) {
                parseRes.add(MarkdownElement.Text(textContent))
            }

            val srcID = matchRes.groupValues[2].ifEmpty { matchRes.groupValues[3] }
            parseRes.add(MarkdownElement.Image(srcID))

            prevIdx = matchRes.range.last + 1
        }

        if(prevIdx < markdownContent.length) {
            val textContent = markdownContent.substring(prevIdx)
            parseRes.add(MarkdownElement.Text(textContent))
        }

        return parseRes
    }

    fun getPostImage(type: String, id: String, srcID: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(postImageMap = it.postImageMap + (srcID to null)) }

            val postImage = repository.getPostImage(type, id, srcID)
            if(postImage == null) {
                Log.e(LOG_TAG, "PostImage [$id] got error")
                return@launch
            }

            val postImageBitmap = postImage.base64Str.let { base64Str ->
                try {
                    val imageBytes = Base64.decode(base64Str)

                    val bitmapOptions = BitmapFactory.Options()
                    bitmapOptions.inJustDecodeBounds = true
                    BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size, bitmapOptions)
                    bitmapOptions.inSampleSize = downscaleBitmap(bitmapOptions)
                    bitmapOptions.inJustDecodeBounds = false
                    BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size, bitmapOptions)
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                    null
                }
            }

            _uiState.update { it.copy(postImageMap = it.postImageMap + (srcID to postImageBitmap)) }
        }
    }

    private fun downscaleBitmap(options: BitmapFactory.Options): Int {
        val origWidth: Int = options.outWidth
        var inSampleSize = 1

        if(origWidth > BITMAP_DOWNSCALE_WIDTH) {
            val halfWidth: Int = origWidth / 2
            while((halfWidth / inSampleSize) >= origWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}