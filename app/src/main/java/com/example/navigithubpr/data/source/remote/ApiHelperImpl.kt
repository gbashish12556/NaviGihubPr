package com.example.navigithubpr.data.source.remote

import com.example.navigithubpr.data.response.GithubIssuesResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllResponse(
        orgname: String?,
        repoName: String?,
        state: String?
    ): Response<List<GithubIssuesResponse>> {

        return apiService.getAllResponse(orgname,repoName,state);

    }


}