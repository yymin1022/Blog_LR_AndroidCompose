package com.yong.blog.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yong.blog.common.ui.BlogAppBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToList: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BlogAppBar(
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        MainScreenBody(
            modifier = Modifier
                .padding(innerPadding),
            navigateToList = navigateToList
        )
    }
}

@Composable
private fun MainScreenBody(
    modifier: Modifier = Modifier,
    navigateToList: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text("Main")
        Button(
            onClick = { navigateToList("blog") }
        ){
            Text("Go to Blog List")
        }
        Button(
            onClick = { navigateToList("project") }
        ){
            Text("Go to Project List")
        }
        Button(
            onClick = { navigateToList("about") }
        ){
            Text("Go to About List")
        }
    }
}