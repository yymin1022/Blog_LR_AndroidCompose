package com.yong.blog.detail.ui.markdown

import android.content.Context
import android.graphics.Bitmap
import android.text.style.ImageSpan
import androidx.core.graphics.drawable.toDrawable
import io.noties.markwon.MarkwonConfiguration
import io.noties.markwon.RenderProps
import io.noties.markwon.html.HtmlTag
import io.noties.markwon.html.tag.SimpleTagHandler

class ImageTagHandler(
    private val context: Context,
    private val imageHeight: Int,
    private val getPostImage: (srcID: String) -> Bitmap?,
    private val requestPostImage: (String) -> Unit
): SimpleTagHandler() {
    override fun getSpans(
        configuration: MarkwonConfiguration,
        renderProps: RenderProps,
        tag: HtmlTag
    ): Any? {
        val srcID = tag.attributes()["src"] ?: return null
        val imageBitmap = getPostImage(srcID)

        if(imageBitmap == null) {
            requestPostImage(srcID)
            return null
        }

        val imageDrawable = imageBitmap.toDrawable(context.resources)
        val origHeight = imageDrawable.intrinsicHeight
        val origWidth = imageDrawable.intrinsicWidth

        if(origWidth > 0) {
            val newWidth = (imageHeight.toFloat() / origHeight * origWidth).toInt()
            imageDrawable.setBounds(0, 0, newWidth, imageHeight)
        }

        return ImageSpan(imageDrawable)
    }

    override fun supportedTags(): Collection<String> {
        return setOf("img")
    }
}