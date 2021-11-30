package com.example.navigithubpr

import android.app.Application
import com.example.navigithubpr.data.Source.PrRepository
import timber.log.Timber

class PrApplication : Application() {

    val prRepository: PrRepository
        get() = ServiceLocator.provideTasksRepository(this)

    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}