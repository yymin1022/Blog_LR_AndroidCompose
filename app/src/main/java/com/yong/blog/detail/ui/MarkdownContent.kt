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
import ca.blarg.prism4j.languages.Prism4jGrammarLocator
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.syntax.Prism4jThemeDarkula
import io.noties.markwon.syntax.SyntaxHighlightPlugin
import io.noties.prism4j.Prism4j

@Composable
fun MarkdownContent(
    modifier: Modifier = Modifier,
    markdownContent: String,
    postImageMap: Map<String, Bitmap?>,
    requestPostImage: (String) -> Unit
) {
    val context = LocalContext.current

    val prism4j = remember { Prism4j(Prism4jGrammarLocator()) }
    val markwon = remember {
        Markwon.builder(context)
            .usePlugin(HtmlPlugin.create())
            .usePlugin(SyntaxHighlightPlugin.create(prism4j, Prism4jThemeDarkula.create()))
            .build()
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