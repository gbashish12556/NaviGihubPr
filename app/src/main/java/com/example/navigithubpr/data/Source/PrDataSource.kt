package com.example.navigithubpr.data.Source

import com.example.navigithubpr.data.GithubIssuesResponse
import com.example.navigithubpr.data.Result

interface PrDataSource {

    suspend fun getTasks(orgaName:String, folder:String): Result<List<GithubIssuesResponse>>


}