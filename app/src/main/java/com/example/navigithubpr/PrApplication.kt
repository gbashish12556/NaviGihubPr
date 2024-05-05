package com.example.navigithubpr

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PrApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}