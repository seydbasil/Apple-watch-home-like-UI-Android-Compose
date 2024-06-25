package com.smbvt.bst.applewatchhomeuiandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.smbvt.bst.applewatchhomeuiandroid.domain.Countries
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.AppleWatchGridLayout
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.AppleWatchGridLayoutInitialState
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.CountryItem
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.ItemSize100
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.White
import com.smbvt.bst.applewatchhomeuiandroid.utils.Utils

@Composable
fun CountryScreen(
    countries: Countries = Countries(listOf()),
    firstVisibleItemIndex: Int = 25
) {

    var centerItem by remember {
        mutableIntStateOf(-1)
    }

    val list = countries.companiesList ?: listOf()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        androidx.compose.animation.AnimatedVisibility(visible = list.isNotEmpty()) {
            AppleWatchGridLayout(
                modifier = Modifier.fillMaxSize(),
                rowItemsCount = Utils.calculateNumberOfColumns(list.size),
                itemSize = ItemSize100,
                content = {
                    list.forEachIndexed { index, pair ->
                        CountryItem(data = pair, isCenter = centerItem == index) {
                            centerItem = index

                        }
                    }
                },
                updateItemIndex = {
                    centerItem = it
                }, appleWatchGridLayoutInitialState = AppleWatchGridLayoutInitialState(firstVisibleItemIndex)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White, Color(0x00000000)
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0x00000000), Color.White,
                            )
                        )
                    )
            )
        }
    }
}


@Composable
@Preview
fun PreviewCountryScreen() {
    Box(modifier = Modifier.background(color = Color.White)) {
        CountryScreen(
            Countries(listOf())
        )
    }
}