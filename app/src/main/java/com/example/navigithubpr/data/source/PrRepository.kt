package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.Result
import com.example.navigithubpr.data.response.GithubIssuesResponse

interface PrRepository {

    suspend fun getTasks(orgName:String, folderName:String): Result<List<GithubIssuesResponse>>

}
