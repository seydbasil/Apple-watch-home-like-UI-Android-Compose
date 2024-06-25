package com.smbvt.bst.applewatchhomeuiandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.smbvt.bst.applewatchhomeuiandroid.ui.screens.IndividualCompanyDetailsScreen
import com.smbvt.bst.applewatchhomeuiandroid.ui.theme.AppleWatchHomeUIAndroidTheme
import com.smbvt.bst.applewatchhomeuiandroid.utils.DataUtils.getDummyData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppleWatchHomeUIAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IndividualCompanyDetailsScreen(investmentOptionCompanies = getDummyData())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppleWatchHomeUIAndroidTheme {
        Greeting("Android")
    }
}