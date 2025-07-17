package com.yong.blog.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(
    postType: String,
    postID: String,
    onNavigateToList: () -> Unit,
    onNavigateToMain: () -> Unit
) {
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
    }
}