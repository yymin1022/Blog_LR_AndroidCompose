package com.yong.blog.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.repository.PostDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
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
}