package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.Result
import com.example.navigithubpr.data.UserInput
import retrofit2.Call
import retrofit2.Response

class DefaultPreRepository(private val prDataSource: PrDataSource):PrRepository {
    override fun getTasks(userInput: UserInput): Call<List<GithubIssuesResponse>> {
        return prDataSource.getTasks(userInput)
    }
}