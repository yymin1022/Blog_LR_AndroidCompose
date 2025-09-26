package com.yong.blog.common.util

import android.content.Context
import io.noties.markwon.Markwon

class MarkwonUtil {
    fun createMarkwon(context: Context): Markwon {
        val markwonBuilder = Markwon.builder(context)

        return markwonBuilder.build()
    }
}