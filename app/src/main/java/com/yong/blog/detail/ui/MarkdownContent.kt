package com.yong.blog.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikepenz.markdown.compose.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import com.mikepenz.markdown.model.rememberMarkdownState

@Composable
fun MarkdownContent(
    modifier: Modifier = Modifier,
    markdownContent: String
) {
    val markdownState = rememberMarkdownState(markdownContent)

    Markdown(
        modifier = modifier,
        markdownState = markdownState,
        colors = markdownColor(),
        typography = markdownTypography()
    )
}