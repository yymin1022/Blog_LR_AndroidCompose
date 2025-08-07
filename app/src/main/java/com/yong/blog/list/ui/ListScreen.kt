package com.yong.blog.list.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yong.blog.common.ui.BlogAppBar
import com.yong.blog.domain.model.PostListItem
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
        if(!isLoading) {
            LazyColumn {
                if(postList != null) {
                    items(postList?.postCount ?: 0) { idx ->
                        val post = postList!!.postList[idx]
                        PostListItem(
                            modifier = Modifier,
                            postType = postType,
                            postData = post,
                            onClick = onNavigateToDetail
                        )
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

@Composable
private fun PostListItem(
    modifier: Modifier = Modifier,
    postType: String,
    postData: PostListItem,
    onClick: (String, String) -> Unit
) {
    val postDate = postData.postDate
    val postID = postData.postID
    val postTag = postData.postTag
    val postThumbnail = "TMP Thumbnail"
    val postTitle = postData.postTitle

    Row(
        modifier = modifier
            .clickable(onClick = { onClick(postType, postID) })
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .border(border = BorderStroke(1.dp, Color.LightGray))
    ) {
        PostListItemImage(
            modifier = Modifier
                .fillMaxHeight(),
            postType = postType,
            postID = postID,
            postThumbnailBase64 = postThumbnail
        )
        PostListItemText(
            modifier = Modifier
                .fillMaxSize(),
            postDate = postDate,
            postTag = postTag,
            postTitle = postTitle
        )
    }
}

@Composable
private fun PostListItemImage(
    modifier: Modifier = Modifier,
    postType: String,
    postID: String,
    postThumbnailBase64: String,
) {
    Box(
        modifier = modifier
    ) {
        Text("Image [$postThumbnailBase64]")
    }

}

@Composable
private fun PostListItemText(
    modifier: Modifier = Modifier,
    postDate: String,
    postTag: List<String>,
    postTitle: String
) {
    Column(
        modifier = modifier
    ) {
        Text("Title: $postTitle")
        Text("Date: $postDate")
        Text("Tag: $postTag")
    }
}
