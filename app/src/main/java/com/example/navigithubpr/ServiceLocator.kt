package com.example.navigithubpr

import androidx.annotation.VisibleForTesting
import com.example.navigithubpr.data.source.DefaultPreRepository
import com.example.navigithubpr.data.source.PrDataSource
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.data.source.remote.Api
import com.example.navigithubpr.data.source.remote.RemoteDataSource

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
