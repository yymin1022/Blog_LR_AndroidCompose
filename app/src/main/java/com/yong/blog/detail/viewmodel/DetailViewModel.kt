package com.yong.blog.detail.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yong.blog.R
import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.repository.PostDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64

data class DetailUiState(
    val appBarTitle: Int = R.string.detail_appbar_title,
    val isLoading: Boolean = true,
    val postData: PostData? = null,
    val postImageMap: Map<String, Bitmap?> = emptyMap()
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PostDetailRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    fun getPostData(type: String, id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val postDate = repository.getPostData(type, id)
                _uiState.update { it.copy(postData = postDate) }
            } catch(e: Exception) {
                // TODO: Error Handling
                e.printStackTrace()
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun getPostImage(type: String, id: String, srcID: String) {
        viewModelScope.launch {
            val postImage = repository.getPostImage(type, id, srcID)
            val postImageBitmap = postImage.base64Str.let { base64Str ->
                try {
                    val imageBytes = Base64.decode(base64Str)
                    val bitmapOptions = BitmapFactory.Options()
                    BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size, bitmapOptions)
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                    null
                }
            }

            _uiState.update { it.copy(postImageMap = it.postImageMap + (srcID to postImageBitmap)) }
        }
    }
}