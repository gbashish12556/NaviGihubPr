package com.example.navigithubpr.data.source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.response.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.await

class DefaultPreRepository(private val prRemoteDataSource: PrRemoteDataSource,
                           private val prLocalDataSource: PrLocalDataSource,
                           private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):PrRepository {

    override suspend fun getTasks(userInput: UserInput): Response<List<GithubIssuesResponse>> {

             return prRemoteDataSource.getTasks(userInput)
//        return prLocalDataSource.getPrs()
    }


//    private suspend fun updateTasksFromRemoteDataSource(userInput: UserInput) {
//        withContext(ioDispatcher) {
//            val remoteTasks = prRemoteDataSource.getTasks(userInput)
//            if (remoteTasks.body()!!.size > 0) {
//                prLocalDataSource.deleteAllPrs()
//                prLocalDataSource.insertPrs(remoteTasks.body()!!)
//            }
//        }
//    }
//
//
//
//    override suspend fun refreshTask(userInput: UserInput) {
//        updateTasksFromRemoteDataSource(userInput)
//    }
}