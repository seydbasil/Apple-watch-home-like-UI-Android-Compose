package com.smbvt.bst.applewatchhomeuiandroid.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.smbvt.bst.applewatchhomeuiandroid.R
import com.smbvt.bst.applewatchhomeuiandroid.ui.composables.PaddedContentBox
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Size24
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Steel1
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Steel2
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Steel3
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Steel4
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.buttons.AnimatedTextButton

@Composable
fun MainScreen(onClickAppleWatchUi: () -> Unit = {}, onClickCountries: () -> Unit = {}) {
    PaddedContentBox(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedTextButton(modifier = Modifier,
                text = stringResource(id = R.string.view_apple_watch_ui),
                colors = listOf(
                    Steel1, Steel2, Steel3, Steel4
                ),
                onClick = { onClickAppleWatchUi() })
            Spacer(modifier = Modifier.size(Size24))
            AnimatedTextButton(modifier = Modifier,
                text = stringResource(id = R.string.view_countries),
                colors = listOf(
                    Steel1, Steel2, Steel3, Steel4
                ),
                onClick = { onClickCountries() })
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}