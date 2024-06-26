package com.smbvt.bst.applewatchhomeuiandroid.ui.composables.apple_watch_ui

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
import com.smbvt.bst.applewatchhomeuiandroid.domain.Country
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.AlphaBlack10000000
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.AlphaBlack20000000
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.AlphaBlack70000000
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.BorderWidth3
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.FontSize32
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Padding2
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Padding7
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Padding9
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.Purple4D29D7
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.VeryLightGray
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.White
import com.smbvt.bst.applewatchhomeuiandroid.utils.innerShadow

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CountryItem(
    modifier: Modifier = Modifier,
    data: Country,
    isCenter: Boolean = false,
    onClick: () -> Unit = {}
) {

    val shape = CircleShape
    val background = if (isCenter) White else VeryLightGray
    Box(
        modifier = modifier
            .then(
                if (isCenter) {
                    Modifier
                        .padding(Padding2)
                        .border(width = BorderWidth3, color = AlphaBlack10000000, shape = shape)
                        .padding(Padding7)
                } else {
                    Modifier
                        .padding(Padding9)
                }
            )
            // Bottom right corner shadow.
            .innerShadow(
                shape = CircleShape,
                color = AlphaBlack20000000,
                offsetY = (-2).dp,
                offsetX = (-2).dp
            )
            // Top left corner shadow.
            .innerShadow(
                shape = CircleShape,
                color = AlphaBlack70000000,
                offsetY = 2.dp,
                offsetX = 2.dp
            )
            .background(color = background, shape = shape)
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .clip(shape)
                .fillMaxSize()
                .background(color = background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center,
                text = data.name.firstOrNull()?.toString() ?: stringResource(id = R.string.no_name),
                style = TextStyle(
                    color = Purple4D29D7, fontSize = FontSize32
                ),
                fontWeight = FontWeight.Bold
            )

            var backgroundState by remember {
                mutableStateOf(Color.Transparent)
            }

            GlideImage(
                model = data.icon,
                contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = backgroundState)
                    .clickable(onClick = onClick),
            ) {
                // identify once loading is completed and set white background color for the image view to hide name first letter text
                // while loading or once loading is failed, we are showing name first letter which usually happens when we load image from web url
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
                        backgroundState = background
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
        CountryItem(
            data = Country(name = "A"), modifier = Modifier.size(100.dp)
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
        CountryItem(
            data = Country(), modifier = Modifier.size(100.dp), isCenter = true
        )
    }
}