package com.example.kutumbreadsms.data.source.db

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrLocalDataSource
import com.example.truecreditslist.db.PrDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource internal constructor(
    private val smsDao: PrDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO ) : PrLocalDataSource{

    override fun getSms(): LiveData<List<GithubIssuesResponse>> {
        return smsDao.allPosts()
    }

    override suspend fun deleteAllSms() = withContext(ioDispatcher){
        smsDao.deleteSms()
    }

    override suspend fun insertSms(smsList: List<GithubIssuesResponse>) = withContext(ioDispatcher){
        smsDao.insertAll(smsList)
    }

}