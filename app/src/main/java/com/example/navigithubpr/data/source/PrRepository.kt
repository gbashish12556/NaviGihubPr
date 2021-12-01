package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.Result
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import retrofit2.Call
import retrofit2.Response

interface PrRepository {

    fun getTasks(userInput: UserInput): Call<List<GithubIssuesResponse>>

}
