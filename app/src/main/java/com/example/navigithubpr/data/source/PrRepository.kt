package com.example.navigithubpr.data.source

import com.example.navigithubpr.home.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import retrofit2.Response

interface PrRepository {

    suspend fun getTasks(userInput: UserInput): Response<List<GithubIssuesResponse>>
//    suspend fun refreshTask(userInput: UserInput)

}
