package com.yong.blog.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yong.blog.R
import com.yong.blog.common.ui.theme.BlogBlue

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToList: (String) -> Unit,
    navigateToDetail: (String, String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        MainScreenBody(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navigateToList = navigateToList,
            navigateToDetail = navigateToDetail,
        )
    }
}

@Composable
private fun MainScreenBody(
    modifier: Modifier = Modifier,
    navigateToList: (String) -> Unit,
    navigateToDetail: (String, String) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 40.dp),
    ) {
        MainTitle(
            modifier = Modifier,
        )
        PostListButton(
            modifier = Modifier,
            title = "Blog",
            onClick = { navigateToList("blog") },
        )
        PostListButton(
            modifier = Modifier,
            title = "Project",
            onClick = { navigateToList("project") },
        )
        PostListButton(
            modifier = Modifier,
            title = "About",
            onClick = { navigateToDetail("about", "LR") },
        )
    }
}

@Composable
private fun MainTitle(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(bottom = 20.dp),
    ) {
        MainTitleText(
            title = stringResource(R.string.main_title_1),
        )
        MainTitleText(
            title = stringResource(R.string.main_title_2),
            titleColor = BlogBlue
        )
        MainTitleText(
            title = stringResource(R.string.main_title_3),
        )
    }
}

@Composable
private fun MainTitleText(
    title: String,
    titleColor: Color = Color.Unspecified,
) {
    Text(
        text = title,
        color = titleColor,
        fontSize = 35.sp,
    )
}

@Composable
private fun PostListButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
        )
    }
}