package com.yong.blog.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

private typealias NavEnterTransition = AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition

object BlogNavAnimation {
    private const val ANIMATION_DURATION_MS = 400

    fun enterTransition(): NavEnterTransition = {
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(durationMillis = ANIMATION_DURATION_MS)
        )
    }
}