package com.yong.blog.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.yong.blog.list.viewmodel.ListViewModel

@Composable
fun ListScreen(
    postType: String,
    onNavigateToDetail: (String, String) -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val postList by viewModel.postList.collectAsState()

    LaunchedEffect(postType) {
        viewModel.getPostList(postType)
    }

    Column {
        Text("List (Type: $postType)")
        Button(
            onClick = onNavigateToMain
        ){
            Text("Back to Main")
        }
        if(!isLoading) {
            LazyColumn {
                if(postList != null) {
                    items(postList?.postCount ?: 0) { idx ->
                        val postID = postList!!.postList[idx].postID
                        Button(
                            onClick = { onNavigateToDetail(postType, postID) }
                        ) {
                            Text("Go to Post[$postID]")
                        }
                    }
                } else {
                    item {
                        Text("Nothing")
                    }
                }
            }
        } else {
            CircularProgressIndicator()
        }
    }
}