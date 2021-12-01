package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.Result

interface PrDataSource {

    suspend fun getTasks(orgaName:String, folder:String): Result<List<GithubIssuesResponse>>


}