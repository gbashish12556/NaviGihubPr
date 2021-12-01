package com.example.navigithubpr.data.Source

import com.example.navigithubpr.data.GithubIssuesResponse

class DefaultPreRepository(private val prDataSource: PrDataSource):PrRepository {
    override suspend fun getTasks(forceUpdate: Boolean): Result<List<GithubIssuesResponse>> {
        return prDataSource.getTasks()
    }
}