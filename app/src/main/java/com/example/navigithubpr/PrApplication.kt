package com.example.navigithubpr

import android.app.Application
import timber.log.Timber

class PrApplication : Application() {

    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}