package com.example.navigithubpr.data.source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.UserInput
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class DefaultPreRepository(private val prRemoteDataSource: PrRemoteDataSource,
                           private val prLocalDataSource: PrLocalDataSource,
                           private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):PrRepository {
    override fun getTasks(userInput: UserInput): LiveData<List<GithubIssuesResponse>> {
        return prLocalDataSource.getSms()
    }


    private suspend fun updateTasksFromRemoteDataSource(userInput: UserInput) {
        withContext(ioDispatcher) {
            val remoteTasks = prRemoteDataSource.getTasks(userInput).await()
            if (remoteTasks.size > 0) {
                prLocalDataSource.deleteAllPrs()
                prLocalDataSource.insertPrs(remoteTasks)
            }
        }
    }



    override suspend fun refreshTask(userInput: UserInput) {
        updateTasksFromRemoteDataSource(userInput)
    }
}