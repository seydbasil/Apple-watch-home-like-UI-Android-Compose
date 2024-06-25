package com.smbvt.bst.applewatchhomeuiandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smbvt.bst.applewatchhomeuiandroid.ui.screens.AppleWatchScreen
import com.smbvt.bst.applewatchhomeuiandroid.ui.screens.MainScreen
import com.smbvt.bst.applewatchhomeuiandroid.utils.DataUtils

const val mainScreen = "mainScreen"
const val storyScreen = "storyScreen"

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = mainScreen) {
        composable(mainScreen) {
            MainScreen(onClickViewReels = {
                navController.navigate(storyScreen)
            })
        }
        composable(storyScreen) {
            AppleWatchScreen(investmentOptionCompanies = DataUtils.getDummyData())
            /*  StoryScreen(onClickClose = {
                  navController.navigateUp()
              })*/
        }
    }
}