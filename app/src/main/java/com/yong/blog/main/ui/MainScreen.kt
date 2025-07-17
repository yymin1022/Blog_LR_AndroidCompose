package com.yong.blog.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(
    onNavigateToList: (String) -> Unit
) {
    Column {
        Text("Main")
        Button(
           onClick = { onNavigateToList("TempType") }
        ){
            Text("Go to List")
        }
    }
}