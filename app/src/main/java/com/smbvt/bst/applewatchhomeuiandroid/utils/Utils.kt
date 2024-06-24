package com.smbvt.bst.applewatchhomeuiandroid.utils

import kotlin.math.ceil
import kotlin.math.sqrt

object Utils {
    fun calculateNumberOfColumns(numberOfItems: Int): Int {
        // Use the square root as the number of columns
        return ceil(sqrt(numberOfItems.toDouble())).toInt()
    }
}