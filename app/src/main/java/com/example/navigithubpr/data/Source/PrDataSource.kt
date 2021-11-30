package com.example.navigithubpr.data.Source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.GithubIssuesResponse

interface PrDataSource {

    fun observeTasks(): LiveData<Result<List<GithubIssuesResponse>>>

    suspend fun getTasks(): Result<List<GithubIssuesResponse>>

    suspend fun refreshTasks()

    fun observeTask(taskId: String): LiveData<Result<GithubIssuesResponse>>

    suspend fun getTask(taskId: String): Result<GithubIssuesResponse>

    suspend fun refreshTask(taskId: String)
    
}