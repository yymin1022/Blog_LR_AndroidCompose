package com.yong.blog.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yong.blog.R

@Composable
fun BlogErrorIndicator(
    modifier: Modifier = Modifier,
    errorMessage: String = stringResource(R.string.common_error_message),
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(errorMessage)

        Button(
            modifier = Modifier,
            onClick = onRetry
        ) {
            Text(stringResource(R.string.common_error_retry))
        }
    }
}