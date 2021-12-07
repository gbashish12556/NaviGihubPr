package com.example.navigithubpr

import androidx.annotation.VisibleForTesting
import com.example.navigithubpr.data.source.DefaultPreRepository
import com.example.navigithubpr.data.source.PrRemoteDataSource
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.data.source.remote.Api
import com.example.navigithubpr.data.source.remote.RemoteDataSource
import com.example.navigithubpr.data.source.remote.RemoteNetworkingClient

object ServiceLocator {

    @Volatile
    var prRepository: PrRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(): PrRepository {
        synchronized(this) {
            return prRepository ?: prRepository ?: createTasksRepository()
        }
    }

    private fun createTasksRepository(): PrRepository {
        val newRepo = DefaultPreRepository(createPrRemoteDataSource())
        prRepository = newRepo
        return newRepo
    }

    private fun createPrRemoteDataSource(): PrRemoteDataSource {
        val api: Api = RemoteNetworkingClient.instance!!.api
        return RemoteDataSource(api)
    }


}
