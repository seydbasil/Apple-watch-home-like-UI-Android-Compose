package com.smbvt.bst.applewatchhomeuiandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smbvt.bst.applewatchhomeuiandroid.ui.screens.AppleWatchScreen
import com.smbvt.bst.applewatchhomeuiandroid.ui.screens.CountryScreen
import com.smbvt.bst.applewatchhomeuiandroid.ui.screens.MainScreen
import com.smbvt.bst.applewatchhomeuiandroid.utils.DataUtils

const val mainScreen = "mainScreen"
const val appleWatchScreen = "appleWatchScreen"
const val countryScreen = "countryScreen"

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = mainScreen) {
        composable(mainScreen) {
            MainScreen(onClickAppleWatchUi = {
                navController.navigate(appleWatchScreen)
            }, onClickCountries = {
                navController.navigate(countryScreen)
            })
        }
        composable(appleWatchScreen) {
            AppleWatchScreen(countries = DataUtils.getMenuItems(context))/*  StoryScreen(onClickClose = {
                  navController.navigateUp()
              })*/
        }
        composable(countryScreen) {
            CountryScreen(countries = DataUtils.getCountries())/*  StoryScreen(onClickClose = {
                  navController.navigateUp()
              })*/
        }
    }
}