package com.example.kutumbreadsms.data.source.db

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrLocalDataSource
import com.example.truecreditslist.db.PrDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource internal constructor(
    private val prDao: PrDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO ) : PrLocalDataSource{

    override fun getPrs(): LiveData<List<GithubIssuesResponse>> {
        return prDao.allPrs()
    }

    override suspend fun deleteAllPrs() = withContext(ioDispatcher){
        prDao.deletePrs()
    }

    override suspend fun insertPrs(prList: List<GithubIssuesResponse>) = withContext(ioDispatcher){
        prDao.insertAll(prList)
    }

}