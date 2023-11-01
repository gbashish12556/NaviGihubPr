package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.home.UserInput
import retrofit2.Response

interface PrRemoteDataSource {

    suspend fun getTasks(userInput: UserInput): Response<List<GithubIssuesResponse>>

}