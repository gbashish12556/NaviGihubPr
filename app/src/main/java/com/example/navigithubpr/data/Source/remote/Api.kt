package com.example.navigithubpr.data.Source.remote

import com.example.navigithubpr.data.GithubIssuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/repos/{orgName}/{repoName}/issues")
    fun getAllResponse(
        @Path("orgName") orgname: String?,
        @Path("repoName") repoName: String?,
        @Query("state") state: String?
    ): Call<List<GithubIssuesResponse?>?>?
}