package com.example.navigithubpr.data.source.remote

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.source.PrRemoteDataSource
import retrofit2.Call

class RemoteDataSource(private val api: Api):PrRemoteDataSource {

    override fun getTasks(userInput: UserInput):Call<List<GithubIssuesResponse>> {
      return api.getAllResponse(userInput.orgname, userInput.folderName, userInput.prStatus)
    }

}