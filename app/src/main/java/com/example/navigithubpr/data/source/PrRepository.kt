package com.example.navigithubpr.data.source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.Result
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import retrofit2.Call
import retrofit2.Response

interface PrRepository {

    suspend fun getTasks(userInput: UserInput): Response<List<GithubIssuesResponse>>
//    suspend fun refreshTask(userInput: UserInput)

}
