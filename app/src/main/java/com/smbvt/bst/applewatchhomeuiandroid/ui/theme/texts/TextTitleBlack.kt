package com.smbvt.bst.applewatchhomeuiandroid.ui.theme.texts

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.FontSize17


@Composable
fun TextTitleBlack(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = FontSize17,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        ),
    )
}