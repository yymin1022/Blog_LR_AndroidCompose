package com.yong.blog.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.yong.blog.navigation.BlogNavHost
import com.yong.blog.common.ui.theme.Blog_LRTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val blogNavController = rememberNavController()

            Blog_LRTheme {
                BlogNavHost(
                    modifier = Modifier,
                    navController = blogNavController
                )
            }
        }
    }
}