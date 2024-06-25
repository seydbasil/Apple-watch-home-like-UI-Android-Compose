package com.smbvt.bst.applewatchhomeuiandroid.ui.theme.texts

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.FontSize24
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Red
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Yellow

const val ANIMATION_DURATION = 5000
const val GRADIENT_OFFSET_END = 3000f
const val GRADIENT_OFFSET_START = 0f
const val GRADIENT_WIDTH = 500f

@Composable
fun AnimatedText(text: String, colors: List<Color>) {
    var isStart by remember {
        mutableStateOf(false)
    }
    val gradientOffsetAnimation by animateFloatAsState(
        targetValue = if (isStart) GRADIENT_OFFSET_END else GRADIENT_OFFSET_START,
        animationSpec = infiniteRepeatable(
            animation = tween(
                ANIMATION_DURATION, easing = LinearEasing
            )
        ), // Linear easing for constant progress
        label = "",
    )
    LaunchedEffect(key1 = Unit, block = {
        isStart = true
    })
    val gradientStartOffset = gradientOffsetAnimation
    val gradientEndOffset = gradientStartOffset + GRADIENT_WIDTH
    val textColor = Brush.linearGradient(
        colors = colors,
        tileMode = TileMode.Mirror,
        start = Offset(gradientStartOffset, 0f),
        end = Offset(gradientEndOffset, 0f)
    )
    Text(
        modifier = Modifier,
        text = text,
        style = TextStyle(brush = textColor, fontSize = FontSize24)
    )
}