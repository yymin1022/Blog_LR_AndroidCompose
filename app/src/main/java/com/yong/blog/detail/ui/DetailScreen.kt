package com.yong.blog.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yong.blog.detail.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    postType: String,
    postID: String,
    onNavigateToList: () -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val postData by viewModel.postData.collectAsState()

    LaunchedEffect(postType, postID) {
        viewModel.getPostData(postType, postID)
    }

    Column {
        Text("Detail (Type: $postType, ID: $postID)")
        Button(
            onClick = onNavigateToMain
        ){
            Text("Back to Main")
        }
        Button(
            onClick = onNavigateToList
        ){
            Text("Back to List")
        }

        if(!isLoading) {
            if(postData != null) {
                val postContent = postData!!.postContent
                val postDate = postData!!.postDate
                val postTag = postData!!.postTag
                val postTitle = postData!!.postTitle

                Text("Title [$postTitle]")
                Text("Date [$postDate]")
                Text("Tags [$postTag]")
                Text("=== Content ===\n$postContent")
            }
        } else {
            CircularProgressIndicator()
        }
    }
}