package com.yong.blog.list.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListScreen(
    onNavigateToDetail: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    Text("List")
}