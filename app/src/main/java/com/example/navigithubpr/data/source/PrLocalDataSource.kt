package com.example.navigithubpr.data.source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.response.GithubIssuesResponse

interface PrLocalDataSource {
    fun getPrs(): LiveData<List<GithubIssuesResponse>>
    suspend fun deleteAllPrs()
    suspend fun insertPrs(prList:List<GithubIssuesResponse>)
}