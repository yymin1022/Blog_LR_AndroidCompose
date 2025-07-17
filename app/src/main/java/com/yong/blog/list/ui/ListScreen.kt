package com.yong.blog.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListScreen(
    type: String,
    onNavigateToDetail: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    Column {
        Text("List (Type: $type)")
        Button(
            onClick = onNavigateToMain
        ){
            Text("Back to Main")
        }
        Button(
            onClick = onNavigateToDetail
        ){
            Text("Go to Detail")
        }
    }
}