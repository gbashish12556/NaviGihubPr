package com.example.navigithubpr.data.Source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.GithubIssuesResponse

interface PrRepository {

    suspend fun getTasks(forceUpdate: Boolean = false): Result<List<GithubIssuesResponse>>

}
