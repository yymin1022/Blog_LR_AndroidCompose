package com.yong.blog.detail.ui

import android.graphics.Bitmap
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import io.noties.markwon.Markwon

@Composable
fun MarkdownContent(
    modifier: Modifier = Modifier,
    markdownContent: String,
    postImageMap: Map<String, Bitmap?>,
    requestPostImage: (String) -> Unit
) {
    val context = LocalContext.current
    val markwon = remember {
        Markwon.builder(context).build()
    }

    AndroidView(
        modifier = modifier
            .fillMaxSize(),
        factory = { ctx ->
            TextView(ctx).apply {
                setTextColor(Color.Black.hashCode())
            }
        },
        update = {
            it.text = markwon.toMarkdown(markdownContent)
        }
    )
}