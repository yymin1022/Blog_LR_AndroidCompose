package com.yong.blog.list.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yong.blog.domain.model.PostList
import com.yong.blog.domain.repository.PostListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PostListRepository
): ViewModel() {
    private val _postList = MutableStateFlow<PostList?>(null)
    val postList: StateFlow<PostList?> = _postList.asStateFlow()

    private val _thumbnailMap = MutableStateFlow<Map<String, Bitmap?>>(emptyMap())
    val thumbnailMap: StateFlow<Map<String, Bitmap?>> = _thumbnailMap.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getPostList(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true

            try {
                val postList = repository.getPostList(type)
                _postList.value = postList
            } catch(e: Exception) {
                // TODO: Error Handling
                e.printStackTrace()
            } finally {
                _isLoading.value = false
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

            _thumbnailMap.value += (id to thumbnailBitmap)
        }
    }
}