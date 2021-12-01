package com.example.navigithubpr

import android.app.Application
import com.example.navigithubpr.data.Source.PrRepository
import com.example.navigithubpr.data.Source.remote.Api
import com.example.navigithubpr.data.Source.remote.RemoteNetworkingClient
import timber.log.Timber

class PrApplication : Application() {

    val prRepository: PrRepository
        get() = ServiceLocator.provideTasksRepository(this)

    val api: Api
        get() = RemoteNetworkingClient.instance!!.api


    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}