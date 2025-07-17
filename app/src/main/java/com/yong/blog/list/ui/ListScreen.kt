package com.yong.blog.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListScreen(
    postType: String,
    onNavigateToDetail: (String, String) -> Unit,
    onNavigateToMain: () -> Unit
) {
    Column {
        Text("List (Type: $postType)")
        Button(
            onClick = onNavigateToMain
        ){
            Text("Back to Main")
        }
        Button(
            onClick = { onNavigateToDetail(postType, "TempID") }
        ){
            Text("Go to Detail")
        }
    }
}