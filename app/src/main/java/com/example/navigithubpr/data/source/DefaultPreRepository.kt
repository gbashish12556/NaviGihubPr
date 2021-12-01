package com.example.navigithubpr.data.source

import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.Result

class DefaultPreRepository(private val prDataSource: PrDataSource):PrRepository {
    override suspend fun getTasks(orgName:String, folderName:String): Result<List<GithubIssuesResponse>> {
        return prDataSource.getTasks(orgName, folderName)
    }
}