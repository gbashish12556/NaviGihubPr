package com.example.navigithubpr

import android.app.Application
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.data.source.remote.Api
import com.example.navigithubpr.data.source.remote.RemoteNetworkingClient

class PrApplication : Application() {

    val prRepository: PrRepository
        get() = ServiceLocator.provideTasksRepository(this)

    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}