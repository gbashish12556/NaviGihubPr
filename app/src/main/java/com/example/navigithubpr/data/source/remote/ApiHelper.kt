package com.example.navigithubpr.data.source.remote

import com.example.navigithubpr.data.response.GithubIssuesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

    suspend fun getAllResponse(
        orgname: String?,
        repoName: String?,
       state: String?
    ): Response<List<GithubIssuesResponse>>

}