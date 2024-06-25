package com.smbvt.bst.applewatchhomeuiandroid.domain

import androidx.annotation.Keep


@Keep
data class Countries(
    val companiesList: List<Country>? = null
)

data class Country(
    val id: Int = 0,
    val name: String = "",
    val icon: Any? = "",
) {

}

