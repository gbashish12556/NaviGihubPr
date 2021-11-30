package com.example.navigithubpr.data.Source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.GithubIssuesResponse

interface PrDataSource {

    suspend fun getTasks(): Result<List<GithubIssuesResponse>>


}