package com.smbvt.bst.applewatchhomeuiandroid.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import com.smbvt.bst.applewatchhomeuiandroid.domain.MenuItems
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.AppleWatchGridLayout
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.AppleWatchGridLayoutInitialState
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.MenuItem
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.GrayFF131518
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.GrayFF181B1F
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.GrayFF0F0F0F
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.ItemSize80
import com.smbvt.bst.applewatchhomeuiandroid.utils.Utils

@Composable
fun AppleWatchScreen(
    countries: MenuItems = MenuItems(listOf()),
    firstVisibleItemIndex: Int = 25,
    onClickItem: (index: Int) -> Unit = {}
) {

    var centerItem by remember {
        mutableIntStateOf(-1)
    }

    val list = countries.companiesList ?: listOf()
    var isLoadingCompleted by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayFF181B1F)
    ) {
        androidx.compose.animation.AnimatedVisibility(visible = list.isNotEmpty()) {
            AppleWatchGridLayout(modifier = Modifier.fillMaxSize(),
                rowItemsCount = Utils.calculateNumberOfColumns(list.size),
                itemSize = ItemSize80,
                content = {
                    list.forEachIndexed { index, pair ->
                        MenuItem(data = pair, onClick = {
                            centerItem = index
                            onClickItem(index)
                        })
                    }
                },
                updateItemIndex = {
                    centerItem = it
                },
                appleWatchGridLayoutInitialState = AppleWatchGridLayoutInitialState(
                    firstVisibleItemIndex, false
                ),
                onLoadingCompleted = {
                    isLoadingCompleted = true
                })
        }
        AnimatedVisibility(visible = !isLoadingCompleted,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = GrayFF131518)
            ) {

            }
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            val circleRadius = size.minDimension / 2

            // draw a circle border
            drawCircle(color = GrayFF0F0F0F, radius = circleRadius, style = Stroke(width = 30f))

            val circlePath = Path().apply {
                addOval(Rect(center, circleRadius))
            }
            clipPath(circlePath, clipOp = ClipOp.Difference) {
                drawRect(SolidColor(GrayFF131518))
            }
        }
    }
}


@Composable
@Preview
fun PreviewIndividualCompanyDetailsScreen() {
    Box(modifier = Modifier.background(color = Color.White)) {
        AppleWatchScreen(
            MenuItems(listOf())
        )
    }
}