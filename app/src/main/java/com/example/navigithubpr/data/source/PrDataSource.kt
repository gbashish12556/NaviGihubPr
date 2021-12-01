package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.UserInput
import retrofit2.Call
import retrofit2.Response

interface PrDataSource {

    fun getTasks(userInput: UserInput): Call<List<GithubIssuesResponse>>

}