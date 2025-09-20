package com.yong.blog.list.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yong.blog.R
import com.yong.blog.common.ui.BlogUiStatus
import com.yong.blog.domain.model.PostList
import com.yong.blog.domain.repository.PostListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64

data class ListUiState(
    val appBarTitle: Int? = null,
    val postList: PostList? = null,
    val postThumbnailMap: Map<String, Bitmap?> = emptyMap(),
    val uiStatus: BlogUiStatus = BlogUiStatus.UI_STATUS_NORMAL
)

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PostListRepository
): ViewModel() {
    companion object {
        private val POST_TYPE_RESOURCE_MAP = mapOf(
            "blog" to R.string.post_type_blog,
            "project" to R.string.post_type_project,
            "solving" to R.string.post_type_solving
        )
    }

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()

    fun getAppBarTitle(type: String) {
        _uiState.update { it.copy(appBarTitle = POST_TYPE_RESOURCE_MAP[type]) }
    }

    fun getPostList(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(uiStatus = BlogUiStatus.UI_STATUS_LOADING) }

            try {
                val postList = repository.getPostList(type)
                _uiState.update { it.copy(
                    uiStatus = BlogUiStatus.UI_STATUS_NORMAL,
                    postList = postList
                ) }
            } catch(e: Exception) {
                e.printStackTrace()
                _uiState.update { it.copy(uiStatus = BlogUiStatus.UI_STATUS_ERROR) }
            }
        }
    }

    fun requestPostThumbnail(type: String, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val thumbnailImage = repository.getPostThumbnail(type, id)
            val thumbnailBitmap = thumbnailImage.base64Str.let { base64Str ->
                try {
                    val imageBytes = Base64.decode(base64Str)
                    val bitmapOptions = BitmapFactory.Options().apply { inSampleSize = 2 }
                    BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size, bitmapOptions)
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                    null
                }
            }

            _uiState.update { it.copy(postThumbnailMap = it.postThumbnailMap + (id to thumbnailBitmap)) }
        }
    }
}