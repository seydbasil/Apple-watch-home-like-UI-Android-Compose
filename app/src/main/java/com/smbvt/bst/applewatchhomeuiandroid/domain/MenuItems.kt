package com.smbvt.bst.applewatchhomeuiandroid.domain

import androidx.annotation.Keep


@Keep
data class MenuItems(
    val companiesList: List<Menu>? = null
)

data class Menu(
    val id: Int = 0,
    val name: String = "",
    val icon: Int = 0,
) {

}

