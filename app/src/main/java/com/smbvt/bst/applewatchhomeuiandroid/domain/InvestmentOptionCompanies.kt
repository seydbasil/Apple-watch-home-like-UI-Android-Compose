package com.smbvt.bst.applewatchhomeuiandroid.domain

import androidx.annotation.Keep


@Keep
data class InvestmentOptionCompanies(
    val companiesList: List<CompanyDetails>? = null
)

data class CompanyDetails(
    val id: Int = 0,
    val name: String = "",
    val icon: Any? = "",
) {

}

