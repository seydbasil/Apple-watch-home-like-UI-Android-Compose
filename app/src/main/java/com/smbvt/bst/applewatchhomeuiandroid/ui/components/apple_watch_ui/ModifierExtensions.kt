package com.smbvt.bst.applewatchhomeuiandroid.ui.components.apple_watch_ui

import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.foundation.gestures.drag
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.input.pointer.util.addPointerInputChange
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

fun Modifier.drag(state: OffsetState) = pointerInput(Unit) {
    val decay = splineBasedDecay<Offset>(this)
    val tracker = VelocityTracker()
    coroutineScope {
        awaitEachGesture {
            val pointerId = awaitFirstDown(requireUnconsumed = false).id
            launch {
                state.stop()
            }
            tracker.resetTracking()
            var dragPointerInput: PointerInputChange?
            var overSlop = Offset.Zero
            do {
                dragPointerInput = awaitTouchSlopOrCancellation(
                    pointerId
                ) { change, over ->
                    if (change.positionChange() != Offset.Zero) change.consume()
                    overSlop = over
                }
            } while (dragPointerInput != null && !dragPointerInput.isConsumed)

            dragPointerInput?.let {

                launch {
                    state.snapTo(state.currentOffset.plus(overSlop))
                }

                drag(dragPointerInput.id) { change ->
                    val dragAmount = change.positionChange()
                    launch {
                        state.snapTo(state.currentOffset.plus(dragAmount))
                    }
                    if (change.positionChange() != Offset.Zero) change.consume()
                    tracker.addPointerInputChange(change)
                }
            }

            val (velX, velY) = tracker.calculateVelocity()
            val velocity = Offset(velX, velY)
            val targetOffset = decay.calculateTargetValue(
                typeConverter = Offset.VectorConverter,
                initialValue = state.currentOffset,
                initialVelocity = velocity
            )
            launch {
                state.animateTo(
                    offset = targetOffset,
                    velocity = velocity,
                )
            }
        }
    }
}
