package com.example.navigithubpr

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.example.kutumbreadsms.data.source.db.RoomDataSource
import com.example.navigithubpr.data.source.DefaultPreRepository
import com.example.navigithubpr.data.source.PrLocalDataSource
import com.example.navigithubpr.data.source.PrRemoteDataSource
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.data.source.remote.Api
import com.example.navigithubpr.data.source.remote.RemoteDataSource
import com.example.navigithubpr.data.source.remote.RemoteNetworkingClient
import com.example.truecreditslist.db.PrLocalDb

object ServiceLocator {

    @Volatile
    var prRepository: PrRepository? = null
        @VisibleForTesting set

    private var database: PrLocalDb? = null

    fun provideTasksRepository(prApplication: PrApplication): PrRepository {
        synchronized(this) {
            return prRepository ?: prRepository ?: createTasksRepository(prApplication)
        }
    }

    private fun createTasksRepository(prApplication: PrApplication): PrRepository {
        val newRepo = DefaultPreRepository(createPrRemoteDataSource(), createLocalDataSource(prApplication))
        prRepository = newRepo
        return newRepo
    }

    private fun createPrRemoteDataSource(): PrRemoteDataSource {
        val api: Api = RemoteNetworkingClient.instance!!.api
        return RemoteDataSource(api)
    }

    private fun createLocalDataSource(smsApplication: PrApplication): PrLocalDataSource {
        val database = database ?: createDataBase(smsApplication)
        return RoomDataSource(database.prDao())
    }

    @VisibleForTesting
    fun createDataBase(
        context: Context,
    ): PrLocalDb {
        // Real database using SQLite
        val result = Room.databaseBuilder(
            context.applicationContext,
            PrLocalDb::class.java, "sms_local.db"
        ).build()
        database = result
        return result
    }


}
