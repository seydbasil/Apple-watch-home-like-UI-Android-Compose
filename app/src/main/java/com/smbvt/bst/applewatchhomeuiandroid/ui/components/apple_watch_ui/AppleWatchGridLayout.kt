package com.smbvt.bst.applewatchhomeuiandroid.ui.components.apple_watch_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.smbvt.bst.applewatchhomeuiandroid.domain.CompanyDetails
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.AppleWatchHomeUIAndroidTheme
import com.smbvt.bst.applewatchhomeuiandroid.utils.convertDpToPx

@Composable
fun AppleWatchGridLayout(
    modifier: Modifier = Modifier,
    rowItemsCount: Int,
    itemSize: Dp,
    state: WatchGridState = rememberWatchGridState(),
    content: @Composable () -> Unit,
    updateItemIndex: (index: Int) -> Unit = {},
    firstVisibleItemIndex: Int? = null
) {

    var previousScale: Float = -1.0f

    check(rowItemsCount > 0) { "rowItemsCount must be positive" }
    check(itemSize > 0.dp) { "itemSize must be positive" }

    val itemSizePx = with(LocalDensity.current) { itemSize.roundToPx() }
    val itemConstraints = Constraints.fixed(width = itemSizePx, height = itemSizePx)

    var getDesiredItemPosition: IntOffset? = null

    var size by remember { mutableStateOf(IntSize.Zero) }

    Layout(modifier = modifier
        .onGloballyPositioned { coordinates ->
            size = coordinates.size
        }
        .drag(state)
        .clipToBounds(), content = content) { measurables, layoutConstraints ->
        val placeables = measurables.map { measurable -> measurable.measure(itemConstraints) }
        val cells = List(placeables.size) { index ->
            val x = index % rowItemsCount
            val y = (index - x) / rowItemsCount
            Cell(x, y)
        }

        state.config = WatchGridConfig(
            layoutWidthPx = layoutConstraints.maxWidth,
            layoutHeightPx = layoutConstraints.maxHeight,
            itemSizePx = itemSizePx,
            cells = cells
        )

        layout(layoutConstraints.maxWidth, layoutConstraints.maxHeight) {
            placeables.forEachIndexed { index, placeable ->
                val position = state.getPositionFor(index)
                val scale = state.getScaleFor(position)
                if (scale >= previousScale) {
                    previousScale = scale
                    updateItemIndex(index)
                }

                if (firstVisibleItemIndex != null) {
                    if (getDesiredItemPosition == null) {
                        if (index == firstVisibleItemIndex) {
                            getDesiredItemPosition = position
                        }
                    }
                }

                placeable.placeWithLayer(position = position, layerBlock = {
                    this.scaleX = scale
                    this.scaleY = scale
                })

            }
        }
    }

    val itemSizeHalf = (itemSize / 2)
    val itemSizeHalfInPx = convertDpToPx(itemSizeHalf.value)

    LaunchedEffect(key1 = getDesiredItemPosition, key2 = size, block = {
        getDesiredItemPosition?.let {
            val halfScreenWidthX = size.width / 2
            val halfScreenWidthY = size.height / 2
            val getX = (0 - it.x) + halfScreenWidthX - itemSizeHalfInPx
            val getY = (0 - it.y) + halfScreenWidthY - itemSizeHalfInPx
            state.animateTo(
                offset = Offset(getX, getY),
                velocity = Offset(0f, 0f),
            )
        }
    })
}

@Preview(showBackground = true)
@Composable
fun WatchGridLayoutPreview() {
    AppleWatchHomeUIAndroidTheme {
        AppleWatchGridLayout(modifier = Modifier
            .size(300.dp)
            .background(Color.Black),
            rowItemsCount = 5,
            itemSize = 80.dp,
            content = {
                listOf<CompanyDetails>().forEach { (res, name) ->
                    AppleWatchItem(
                        data = CompanyDetails()
                    )
                }
            })
    }
}