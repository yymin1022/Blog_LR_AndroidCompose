package com.yong.blog.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.model.PostImage
import com.yong.blog.domain.repository.PostDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PostDetailRepository
): ViewModel() {
    private val _postData = MutableStateFlow<PostData?>(null)
    val postData: StateFlow<PostData?> = _postData.asStateFlow()

    private val _postImages = MutableStateFlow<Map<String, PostImage>>(emptyMap())
    val postImages: StateFlow<Map<String, PostImage>> = _postImages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getPostData(type: String, id: String) {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val postDetail = repository.getPostData(type, id)
                _postData.value = postDetail
            } catch(e: Exception) {
                // TODO: Error Handling
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getPostImage(type: String, id: String, srcID: String) {
        viewModelScope.launch {
            try {
                val postImage = repository.getPostImage(type, id, srcID)
                _postImages.value += (srcID to postImage)
            } catch(e: Exception) {
                // TODO: Error Handling
                e.printStackTrace()
            }
        }
    }
}