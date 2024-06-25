package com.smbvt.bst.applewatchhomeuiandroid.ui.theme.buttons

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Gray
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.GrayDark
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Red
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Yellow
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.texts.AnimatedText

@Composable
fun AnimatedTextButton(
    modifier: Modifier = Modifier, text: String, colors: List<Color> = listOf(
        Yellow, Red
    ), onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = if (isSystemInDarkTheme()) Gray else GrayDark)
    ) {
        AnimatedText(text = text, colors = colors)
    }
}