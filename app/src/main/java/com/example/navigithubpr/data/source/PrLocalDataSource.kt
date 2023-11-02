package com.example.navigithubpr.data.source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.response.GithubIssuesResponse
import kotlinx.coroutines.flow.Flow

interface PrLocalDataSource {
    fun getPrs(): Flow<List<GithubIssuesResponse>>
    suspend fun deleteAllPrs()
    suspend fun insertPrs(prList:List<GithubIssuesResponse>)
}