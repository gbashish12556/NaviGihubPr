package com.example.navigithubpr.data.source.remote

import com.example.navigithubpr.data.response.GithubIssuesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("/repos/{orgName}/{repoName}/issues")
    suspend fun getAllResponse(
        @Path("orgName") orgname: String?,
        @Path("repoName") repoName: String?,
        @Query("state") state: String?
    ): Response<List<GithubIssuesResponse>>
}