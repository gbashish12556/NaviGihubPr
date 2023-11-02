package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.home.UserInput
import com.example.navigithubpr.utils.NetworkHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class DefaultPreRepository(private val prRemoteDataSource: PrRemoteDataSource,
                           private val prLocalDataSource: PrLocalDataSource,
                           private val networkHelper: NetworkHelper,
                           private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):PrRepository {

    override suspend fun getTasks(userInput: UserInput): Flow<List<GithubIssuesResponse>> {
        refreshTask(userInput)
        return prLocalDataSource.getPrs()
    }


    private suspend fun updateTasksFromRemoteDataSource(userInput: UserInput) {
        withContext(ioDispatcher) {
            if(networkHelper.isNetworkConnected()) {
                val remoteTasks = prRemoteDataSource.getTasks(userInput)
                if (remoteTasks.body()!!.size > 0) {
                    prLocalDataSource.deleteAllPrs()
                    prLocalDataSource.insertPrs(remoteTasks.body()!!)
                }
            }
        }
    }



    override suspend fun refreshTask(userInput: UserInput) {
        updateTasksFromRemoteDataSource(userInput)
    }
}