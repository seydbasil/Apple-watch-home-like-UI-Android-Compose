package com.smbvt.bst.applewatchhomeuiandroid.ui.components.apple_watch_ui

import androidx.compose.ui.unit.IntOffset
import com.smbvt.bst.applewatchhomeuiandroid.ui.components.apple_watch_ui.OffsetState
import com.smbvt.bst.applewatchhomeuiandroid.ui.components.apple_watch_ui.WatchGridConfig

interface WatchGridState : OffsetState {

    var config: WatchGridConfig

    fun getPositionFor(index: Int): IntOffset
    fun getScaleFor(position: IntOffset): Float
}