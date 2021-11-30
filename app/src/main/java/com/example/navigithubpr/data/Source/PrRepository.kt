package com.example.navigithubpr.data.Source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.GithubIssuesResponse

interface PrRepository {


    fun observeTasks(): LiveData<Result<List<GithubIssuesResponse>>>

    suspend fun getTasks(forceUpdate: Boolean = false): Result<List<GithubIssuesResponse>>

    suspend fun refreshTasks()

    fun observeTask(taskId: String): LiveData<Result<GithubIssuesResponse>>
}
