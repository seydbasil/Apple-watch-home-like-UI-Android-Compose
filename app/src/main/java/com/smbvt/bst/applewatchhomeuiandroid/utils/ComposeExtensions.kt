package com.smbvt.bst.applewatchhomeuiandroid.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity


@Composable
fun convertDpToPx(itemSizeHalf: Float): Float {
    val density = LocalDensity.current.density
    return itemSizeHalf * density / 2
}