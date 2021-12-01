package com.example.navigithubpr

import android.app.Application
import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.example.navigithubpr.data.Source.DefaultPreRepository
import com.example.navigithubpr.data.Source.PrDataSource
import com.example.navigithubpr.data.Source.PrRepository
import com.example.navigithubpr.data.Source.remote.Api
import com.example.navigithubpr.data.Source.remote.RemoteDataSource
import kotlinx.coroutines.runBlocking

object ServiceLocator {

    @Volatile
    var prRepository: PrRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(prApplication: PrApplication): PrRepository {
        synchronized(this) {
            return prRepository ?: prRepository ?: createTasksRepository(prApplication)
        }
    }

    private fun createTasksRepository(prApplication: PrApplication): PrRepository {
        val newRepo = DefaultPreRepository(createPrRemoteDataSource(prApplication))
        prRepository = newRepo
        return newRepo
    }

    private fun createPrRemoteDataSource(prApplication: PrApplication): PrDataSource {
        val api: Api = prApplication.api
        return RemoteDataSource(api)
    }


}
