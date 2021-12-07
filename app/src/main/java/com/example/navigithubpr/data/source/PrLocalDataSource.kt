package com.example.navigithubpr.data.source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.response.GithubIssuesResponse

interface PrLocalDataSource {
    fun getSms(): LiveData<List<GithubIssuesResponse>>
    suspend fun deleteAllSms()
    suspend fun insertSms(smsList:List<GithubIssuesResponse>)
}