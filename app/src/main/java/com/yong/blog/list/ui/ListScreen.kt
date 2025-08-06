package com.yong.blog.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import com.yong.blog.list.viewmodel.ListViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    postType: String,
    onNavigateToDetail: (String, String) -> Unit,
    onNavigateToMain: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BlogAppBar(
                modifier = Modifier,
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateToMain
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ListScreenBody(
            modifier = Modifier
                .padding(innerPadding),
            postType = postType,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}

@Composable
private fun ListScreenBody(
    modifier: Modifier = Modifier,
    postType: String,
    onNavigateToDetail: (String, String) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val postList by viewModel.postList.collectAsState()

    LaunchedEffect(postType) {
        viewModel.getPostList(postType)
    }

    Column(
        modifier = modifier
    ) {
        Text("List (Type: $postType)")
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