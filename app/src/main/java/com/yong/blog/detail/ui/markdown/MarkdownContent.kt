package com.yong.blog.detail.ui.markdown

import android.graphics.Bitmap
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import ca.blarg.prism4j.languages.Prism4jGrammarLocator
import com.yong.blog.detail.viewmodel.MarkdownElement
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.syntax.Prism4jThemeDarkula
import io.noties.markwon.syntax.SyntaxHighlightPlugin
import io.noties.prism4j.Prism4j

@Composable
fun MarkdownContent(
    modifier: Modifier = Modifier,
    markdownContent: List<MarkdownElement>,
    postImageMap: Map<String, Bitmap?>,
    requestPostImage: (String) -> Unit
) {
    val context = LocalContext.current
    val markwon = remember {
        val prism4j = Prism4j(Prism4jGrammarLocator())
        val syntaxHighlightPlugin = SyntaxHighlightPlugin.create(prism4j, Prism4jThemeDarkula.create())

        Markwon.builder(context)
            .usePlugin(syntaxHighlightPlugin)
            .usePlugin(HtmlPlugin.create())
            .build()
    }

    Box(
        modifier = modifier
    ) {
        Column {
            markdownContent.forEach { element ->
                when(element) {
                    is MarkdownElement.Text -> {
                        MarkdownContentText(
                            modifier = Modifier,
                            markwon = markwon,
                            markdownContent = element.markdownContent
                        )
                    }
                    is MarkdownElement.Image -> {
                        MarkdownContentImage(
                            modifier = Modifier,
                            imageBitmap = postImageMap[element.srcID],
                            onRequestImage = { requestPostImage(element.srcID) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MarkdownContentImage(
    modifier: Modifier = Modifier,
    imageBitmap: Bitmap?,
    onRequestImage: () -> Unit
) {
    LaunchedEffect(Unit) {
        onRequestImage()
    }

    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp),
        contentAlignment = Alignment.Center
    ) {
        if(imageBitmap != null) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                bitmap = imageBitmap.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        } else {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun MarkdownContentText(
    modifier: Modifier = Modifier,
    markwon: Markwon,
    markdownContent: String
) {
    AndroidView(
        modifier = modifier,
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