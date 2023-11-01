package com.example.navigithubpr


sealed class Screen(val route:String) {

    object HomeScreen:Screen("home_screen")
    object PrListScreen:Screen("pr_list_screen")

}