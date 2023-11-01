package com.example.navigithubpr.data.source.remote

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.home.UserInput
import com.example.navigithubpr.data.source.PrRemoteDataSource
import retrofit2.Response

class RemoteDataSource(private val apiHeler: ApiHelper):PrRemoteDataSource {

    override suspend fun getTasks(userInput: UserInput):Response<List<GithubIssuesResponse>> {
      return apiHeler.getAllResponse(userInput.orgName, userInput.folderName, userInput.prStatus)
    }

}