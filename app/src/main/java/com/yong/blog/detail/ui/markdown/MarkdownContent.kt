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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.yong.blog.common.util.MarkwonUtil
import com.yong.blog.detail.viewmodel.MarkdownElement
import io.noties.markwon.Markwon

@Composable
fun MarkdownContent(
    modifier: Modifier = Modifier,
    markdownContent: List<MarkdownElement>,
    postImageMap: Map<String, Bitmap?>,
) {
    val context = LocalContext.current
    val markwon = remember { MarkwonUtil.createMarkwon(context) }

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
) {
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
                modifier = Modifier
                    .fillMaxWidth(),
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
    val textColor = MaterialTheme.colorScheme.onBackground

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            TextView(ctx)
        },
        update = { textView ->
            textView.text = markwon.toMarkdown(markdownContent)
            textView.setTextColor(textColor.toArgb())
        }
    )
}