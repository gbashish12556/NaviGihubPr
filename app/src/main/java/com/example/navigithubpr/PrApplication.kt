package com.example.navigithubpr

import android.app.Application
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.data.source.remote.ApiHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class PrApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}