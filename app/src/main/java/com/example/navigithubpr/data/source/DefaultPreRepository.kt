package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.UserInput
import retrofit2.Call

class DefaultPreRepository(private val prDataSource: PrRemoteDataSource):PrRepository {
    override fun getTasks(userInput: UserInput): Call<List<GithubIssuesResponse>> {
        return prDataSource.getTasks(userInput)
    }
}