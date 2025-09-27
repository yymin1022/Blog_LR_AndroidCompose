package com.yong.blog.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Blue60,
    onPrimary = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = Gray80,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue80,
    onPrimary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Gray30
)

@Composable
fun Blog_LRTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}