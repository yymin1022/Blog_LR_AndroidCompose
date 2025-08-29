package com.yong.blog.detail.ui

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikepenz.markdown.compose.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import com.mikepenz.markdown.model.rememberMarkdownState

@Composable
fun MarkdownContent(
    modifier: Modifier = Modifier,
    markdownContent: String,
    postImageMap: Map<String, Bitmap?>,
    requestPostImage: (String, String, String) -> Unit
) {
    val markdownState = rememberMarkdownState(markdownContent)

    Markdown(
        modifier = modifier,
        markdownState = markdownState,
        colors = markdownColor(),
        typography = markdownTypography()
    )
}