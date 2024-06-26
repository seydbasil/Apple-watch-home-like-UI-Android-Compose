package com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.smbvt.bst.applewatchhomeuiandroid.domain.Menu
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.ItemSize80
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Padding5
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Padding7

@Composable
fun MenuItem(
    modifier: Modifier = Modifier, data: Menu, onClick: () -> Unit = {}
) {
    val shape = CircleShape
    Image(
        modifier = modifier
            .fillMaxSize() // fillMaxSize is must here for proper working of items. especially if we need to identify center item
            .padding(Padding5)
            .clip(shape).clickable {
                onClick()
            },
        painter = painterResource(id = data.icon),
        contentDescription = null
    )
}


@Preview
@Composable
fun PreviewMenuItemRounded() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        MenuItem(
            data = Menu(), modifier = Modifier.size(ItemSize80)
        )
    }
}

@Preview
@Composable
fun PreviewMenuItemRoundedCenter() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        MenuItem(
            data = Menu(), modifier = Modifier.size(ItemSize80)
        )
    }
}