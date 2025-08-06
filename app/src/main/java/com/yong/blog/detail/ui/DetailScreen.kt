package com.yong.blog.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yong.blog.common.ui.BlogAppBar
import com.yong.blog.detail.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    postType: String,
    postID: String,
    onNavigateToList: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BlogAppBar(
                modifier = Modifier,
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateToList
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actionIcon = {
                    IconButton(
                        onClick = onNavigateToMain
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        DetailScreenBody(
            modifier = Modifier
                .padding(innerPadding),
            postType = postType,
            postID = postID
        )
    }
}

@Composable
private fun DetailScreenBody(
    modifier: Modifier = Modifier,
    postType: String,
    postID: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val postData by viewModel.postData.collectAsState()

    LaunchedEffect(postType, postID) {
        viewModel.getPostData(postType, postID)
    }

    Column(
        modifier = modifier
    ) {
        Text("Detail (Type: $postType, ID: $postID)")

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