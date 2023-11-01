package com.example.navigithubpr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigithubpr.R
import com.example.navigithubpr.home.HomeScreen
import com.example.navigithubpr.prlist.PrListScreen
import com.plcoding.cryptocurrencyappyt.ui.theme.PrListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrListTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.HomeScreen.route ){

                        composable(
                            route = Screen.HomeScreen.route
                        ){
                            HomeScreen(navController = navController)
                        }

                        composable(
                            route = Screen.PrListScreen.route+"/{prDetail}"
                        ){
                            PrListScreen()
                        }

                    }
                }
            }
        }
    }
}