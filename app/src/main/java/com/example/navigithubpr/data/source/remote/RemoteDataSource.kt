package com.example.navigithubpr.data.source.remote

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.Result
import com.example.navigithubpr.data.source.PrDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val api: Api,
                       private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO ):PrDataSource {

    override suspend fun getTasks(orgName:String,folder:String):
            Result<List<GithubIssuesResponse>> = withContext(ioDispatcher)  {
        val call1 = api.getAllResponse(orgName, folder, "all")
        call1!!.enqueue(object : Callback<List<GithubIssuesResponse?>?> {
            override fun onResponse(
                call: Call<List<GithubIssuesResponse?>?>?,
                response: Response<List<GithubIssuesResponse?>?>?
            ) {
                if (response?.code() == 200) {
                    val reponse = response.body()
                    if (reponse?.size!! > 0) {
                        Result.Success((reponse as List<GithubIssuesResponse>))
                    } else {
                        Error(Exception("No Data Found"))
                    }
                } else {
                    Error(Exception(response!!.message()))
                }
            }

            override fun onFailure(call: Call<List<GithubIssuesResponse?>?>?, t: Throwable?) {
                Error(Exception(t!!.localizedMessage))
            }

        })
    } as Result<List<GithubIssuesResponse>>
}