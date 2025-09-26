package com.yong.blog.common.util

import android.content.Context
import android.text.util.Linkify
import ca.blarg.prism4j.languages.Prism4jGrammarLocator
import io.noties.markwon.Markwon
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.ext.tables.TableTheme
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.linkify.LinkifyPlugin
import io.noties.markwon.syntax.Prism4jThemeDarkula
import io.noties.markwon.syntax.SyntaxHighlightPlugin
import io.noties.prism4j.Prism4j

class MarkwonUtil {
    fun createMarkwon(context: Context): Markwon {
        val markwonBuilder = Markwon.builder(context)
            .usePlugin(createHtmlPlugin())
            .usePlugin(createLinkifyPlugin())
            .usePlugin(createStrikethroughPlugin())
            .usePlugin(createTablePlugin())
            .usePlugin(createSyntaxHighlightPlugin())

        return markwonBuilder.build()
    }

    private fun createHtmlPlugin(): HtmlPlugin {
        return HtmlPlugin.create()
    }

    private fun createLinkifyPlugin(): LinkifyPlugin {
        return LinkifyPlugin.create(
            Linkify.EMAIL_ADDRESSES
                or Linkify.PHONE_NUMBERS
                or Linkify.WEB_URLS
        )
    }

    private fun createStrikethroughPlugin(): StrikethroughPlugin {
        return StrikethroughPlugin.create()
    }

    private fun createTablePlugin(): TablePlugin {
        val tableTheme = TableTheme.Builder()
            .tableBorderWidth(1)
            .tableCellPadding(-10)
            .build()

        return TablePlugin.create(tableTheme)
    }

    private fun createSyntaxHighlightPlugin(): SyntaxHighlightPlugin {
        val prism4j = Prism4j(Prism4jGrammarLocator())

        return SyntaxHighlightPlugin.create(
            prism4j,
            Prism4jThemeDarkula.create()
        )
    }
}