package com.smbvt.bst.applewatchhomeuiandroid.ui.components.apple_watch_ui

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.smbvt.bst.applewatchhomeuiandroid.R
import com.smbvt.bst.applewatchhomeuiandroid.domain.CompanyDetails
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Black212121
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.BlackAlpha40
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Blue5F3EDB
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.ColorAlpha305F3EDB
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Elevation8
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.FontSize32
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Padding24
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Padding7
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Purple4D29D7
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Width1

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AppleWatchItem(
    modifier: Modifier = Modifier,
    data: CompanyDetails,
    isCenter: Boolean = false,
    onClick: () -> Unit = {}
) {

    val shape = CircleShape

    Box(
        modifier = modifier
            .then(
                if (isCenter) {
                    Modifier
                        .border(
                            brush = Brush.verticalGradient(
                                listOf(
                                    ColorAlpha305F3EDB, Color.Transparent
                                )
                            ), width = Width1, shape = CircleShape
                        )
                        .padding(Padding7)
                        .shadow(
                            elevation = Padding24,
                            shape = shape,
                            ambientColor = Black212121,
                            spotColor = Black212121
                        )
                        .border(
                            brush = Brush.verticalGradient(listOf(Blue5F3EDB, Color.Transparent)),
                            width = Width1,
                            shape = CircleShape
                        )
                } else {
                    Modifier
                        .padding(Padding7)
                        .shadow(
                            elevation = Elevation8,
                            shape = shape,
                            ambientColor = BlackAlpha40,
                            spotColor = BlackAlpha40
                        )
                }
            )
            .background(color = Color.White, shape = shape)
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .clip(shape)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center,
                text = if (data.name.isNotEmpty()) data.name.substring(
                    0, 1
                ) else stringResource(id = R.string.no_name),
                style = TextStyle(
                    color = Purple4D29D7, fontSize = FontSize32
                ),
                fontWeight = FontWeight.Bold
            )

            var background by remember {
                mutableStateOf(Color.Transparent)
            }

            GlideImage(
                model = data.icon,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = background)
                    .clickable(onClick = onClick),
            ) {
                it.listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        background = Color.White
                        return false
                    }

                })
            }
        }
    }
}


@Preview
@Composable
fun PreviewIconRounded() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        AppleWatchItem(
            data = CompanyDetails(), modifier = Modifier.size(100.dp)
        )
    }
}

@Preview
@Composable
fun PreviewIconRoundedCenter() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        AppleWatchItem(
            data = CompanyDetails(), modifier = Modifier.size(100.dp), isCenter = true
        )
    }
}