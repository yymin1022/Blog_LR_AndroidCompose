package com.yong.blog.list.ui

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yong.blog.common.ui.BlogAppBar
import com.yong.blog.common.ui.theme.BlogBlue
import com.yong.blog.domain.model.PostList
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
    val thumbnailMap by viewModel.thumbnailMap.collectAsState()

    LaunchedEffect(postType) {
        viewModel.getPostList(postType)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 4.dp)
    ) {
        if(!isLoading) {
            PostList(
                modifier = Modifier,
                postType = postType,
                postList = postList,
                thumbnailMap = thumbnailMap,
                requestPostThumbnail = viewModel::requestPostThumbnail,
                onNavigateToDetail = onNavigateToDetail
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun PostList(
    modifier: Modifier = Modifier,
    postType: String,
    postList: PostList?,
    thumbnailMap: Map<String, Bitmap?>,
    requestPostThumbnail: (String, String) -> Unit,
    onNavigateToDetail: (String, String) -> Unit
) {
    if(postList != null) {
        LazyColumn(
            modifier = modifier
        ) {
            items(postList.postCount) { idx ->
                val post = postList.postList[idx]
                val postURL = post.postURL
                requestPostThumbnail(postType, postURL)

                PostListItem(
                    modifier = Modifier,
                    postType = postType,
                    postData = post,
                    postThumbnail = thumbnailMap[postURL],
                    onClick = onNavigateToDetail
                )
            }
        }
    } else {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text("Nothing")
        }
    }
}

@Composable
private fun PostListItem(
    modifier: Modifier = Modifier,
    postType: String,
    postData: PostListItem,
    postThumbnail: Bitmap?,
    onClick: (String, String) -> Unit
) {
    val postDate = postData.postDate
    val postID = postData.postID
    val postTag = postData.postTag
    val postTitle = postData.postTitle

    Row(
        modifier = modifier
            .height(96.dp)
            .clickable(onClick = { onClick(postType, postID) })
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .border(border = BorderStroke(0.3.dp, Color.LightGray))
    ) {
        PostListItemImage(
            modifier = Modifier,
            postThumbnail = postThumbnail
        )
        PostListItemText(
            modifier = Modifier,
            postDate = postDate,
            postTag = postTag,
            postTitle = postTitle
        )
    }
}

@Composable
private fun PostListItemImage(
    modifier: Modifier = Modifier,
    postThumbnail: Bitmap?,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        if(postThumbnail != null) {
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = postThumbnail.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        } else {
            CircularProgressIndicator()
        }
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
            .fillMaxSize()
    ) {
        PostListItemTextTitle(
            modifier = Modifier,
            postTitle = postTitle
        )
        PostListItemTextDate(
            modifier = Modifier,
            postDate = postDate
        )
        PostListItemTextTag(
            modifier = Modifier,
            postTag = postTag
        )
    }
}

@Composable
private fun PostListItemTextDate(
    modifier: Modifier = Modifier,
    postDate: String
) {
    Text(
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 2.dp),
        text = postDate,
        fontSize = 12.sp,
        style = TextStyle(
            color = Color.DarkGray
        )
    )
}

@Composable
private fun PostListItemTextTag(
    modifier: Modifier = Modifier,
    postTag: List<String>
) {
    Text(
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 2.dp),
        text = postTag.toString(),
        fontSize = 10.sp,
        style = TextStyle(
            color = Color.Gray
        )
    )
}

@Composable
private fun PostListItemTextTitle(
    modifier: Modifier = Modifier,
    postTitle: String
) {
    Text(
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 4.dp),
        text = postTitle,
        fontSize = 16.sp,
        style = TextStyle(
            color = BlogBlue
        )
    )
}