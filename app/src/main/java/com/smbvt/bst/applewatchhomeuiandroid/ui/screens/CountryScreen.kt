package com.smbvt.bst.applewatchhomeuiandroid.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.smbvt.bst.applewatchhomeuiandroid.domain.Countries
import com.smbvt.bst.applewatchhomeuiandroid.domain.Country
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.AppleWatchGridLayout
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.AppleWatchGridLayoutInitialState
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui.CountryItem
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.AlphaBlack20000000
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Height60
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.ItemSize100
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Padding7
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.White
import com.smbvt.bst.applewatchhomeuiandroid.utils.Utils

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CountryScreen(
    countries: Countries = Countries(listOf()), firstVisibleItemIndex: Int = 78
) {

    var centerItem by remember {
        mutableIntStateOf(-1)
    }

    val list = countries.companiesList ?: listOf()

    val model by remember(centerItem) {
        mutableStateOf(if (centerItem != -1) list[centerItem] else Country())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
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
                    },
                    appleWatchGridLayoutInitialState = AppleWatchGridLayoutInitialState(
                        firstVisibleItemIndex
                    )
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
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
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(Height60)
                .background(color = AlphaBlack20000000)
                .padding(Padding7),
            model = model.icon,
            contentScale = ContentScale.Inside,
            contentDescription = null
        )
        Log.e("____________", "model ${model.name}")
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