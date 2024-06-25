package com.smbvt.bst.applewatchhomeuiandroid.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @PaddedContentBox adds system bars padding like status bar and navigation bar padding
 * Use this composable to add system bars to each screen instead if using padding for whole activity ui. If-
 * so we can add and remove padding for each screen as per needs
 */
@Composable
fun PaddedContentBox(modifier: Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(modifier = modifier.systemBarsPadding(), content = content)
}