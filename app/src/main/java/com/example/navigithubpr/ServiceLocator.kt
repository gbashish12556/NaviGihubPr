package com.example.navigithubpr

import android.app.Application
import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.example.navigithubpr.data.Source.DefaultPreRepository
import com.example.navigithubpr.data.Source.PrDataSource
import com.example.navigithubpr.data.Source.PrRepository
import com.example.navigithubpr.data.Source.remote.RemoteDataSource
import kotlinx.coroutines.runBlocking

object ServiceLocator {
    
    @Volatile
    var prRepository: PrRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(application: Application): PrRepository {
        synchronized(this) {
            return prRepository ?: prRepository ?: createTasksRepository(application)
        }
    }

    private fun createTasksRepository(application: Application): PrRepository {
        val newRepo = DefaultPreRepository(createPrRemoteDataSource(application))
        prRepository = newRepo
        return newRepo
    }

    private fun createPrRemoteDataSource(application: Application): PrDataSource {
        return RemoteDataSource(application)
    }


}
