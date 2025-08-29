package com.yong.blog.detail.ui

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yong.blog.common.ui.BlogAppBar
import com.yong.blog.common.ui.BlogLoadingIndicator
import com.yong.blog.detail.viewmodel.DetailViewModel
import com.yong.blog.domain.model.PostData

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    postType: String,
    postID: String,
    onNavigateToList: () -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val appBarTitle = uiState.appBarTitle
    val isLoading = uiState.isLoading
    val postData = uiState.postData
    val postImageMap = uiState.postImageMap

    LaunchedEffect(postType, postID) {
        viewModel.getPostData(postType, postID)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            BlogAppBar(
                modifier = Modifier,
                titleText = stringResource(appBarTitle),
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
            isLoading = isLoading,
            postData = postData,
            postImageMap = postImageMap,
            requestPostImage = viewModel::getPostImage
        )
    }
}

@Composable
private fun DetailScreenBody(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    postData: PostData?,
    postImageMap: Map<String, Bitmap?>,
    requestPostImage: (String, String, String) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        if(!isLoading) {
            if(postData != null) {
                val postContent = postData.postContent
                val postDate = postData.postDate
                val postTag = postData.postTag
                val postTitle = postData.postTitle

                PostTitle(
                    modifier = Modifier,
                    title = postTitle
                )
                PostDate(
                    modifier = Modifier,
                    date = postDate
                )
                PostContentDivider(
                    modifier = Modifier
                )
                PostContent(
                    modifier = Modifier,
                    contentMarkdown = postContent,
                    postImageMap = postImageMap,
                    requestPostImage = requestPostImage
                )
                PostContentDivider(
                    modifier = Modifier
                )
                PostTag(
                    modifier = Modifier,
                    tagList = postTag
                )
            }
        } else {
            BlogLoadingIndicator(modifier = Modifier)
        }
    }
}

@Composable
private fun PostContent(
    modifier: Modifier = Modifier,
    contentMarkdown: String,
    postImageMap: Map<String, Bitmap?>,
    requestPostImage: (String, String, String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        MarkdownContent(
            modifier = Modifier
                .fillMaxSize(),
            markdownContent = contentMarkdown
        )
    }
}

@Composable
private fun PostContentDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    )
}

@Composable
private fun PostDate(
    modifier: Modifier = Modifier,
    date: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date,
            color = Color.DarkGray,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun PostTag(
    modifier: Modifier = Modifier,
    tagList: List<String>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        tagList.forEach { tag ->
            Text(
                modifier = Modifier.padding(horizontal = 2.dp),
                text = "#$tag",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun PostTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 20.sp
        )
    }
}