package com.example.navigithubpr.data.source.remote

import android.util.Log
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.Result
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.source.PrDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val api: Api):PrDataSource {

    override fun getTasks(userInput: UserInput):Call<List<GithubIssuesResponse>> {
      return api.getAllResponse(userInput.orgname, userInput.folderName, userInput.prStatus)
    }

}