package com.example.navigithubpr.data.Source

import com.example.navigithubpr.data.GithubIssuesResponse
import com.example.navigithubpr.data.Result

class DefaultPreRepository(private val prDataSource: PrDataSource):PrRepository {
    override suspend fun getTasks(orgName:String, folderName:String): Result<List<GithubIssuesResponse>> {
        return prDataSource.getTasks(orgName, folderName)
    }
}