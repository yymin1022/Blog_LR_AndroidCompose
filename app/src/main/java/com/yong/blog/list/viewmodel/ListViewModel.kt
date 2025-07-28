package com.yong.blog.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yong.blog.domain.model.PostList
import com.yong.blog.domain.repository.PostListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PostListRepository
): ViewModel() {
    private val _postList = MutableStateFlow<PostList?>(null)
    val postList: StateFlow<PostList?> = _postList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getPostList(type: String) {
        viewModelScope.launch {
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
}