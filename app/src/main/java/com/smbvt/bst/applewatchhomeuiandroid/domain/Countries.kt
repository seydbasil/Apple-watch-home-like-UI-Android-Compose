package com.smbvt.bst.applewatchhomeuiandroid.domain

import androidx.annotation.Keep


@Keep
data class Countries(
    val companiesList: List<Country>? = null
)

data class Country(
    val id: Int = 0,
    val name: String = "",
    // we can use url or drawable int or any other glide image loading library path here
    val icon: Any? = "",
) {

}

