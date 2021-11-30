package com.example.navigithubpr.data.Source

import com.example.navigithubpr.PrApplication
import com.example.navigithubpr.data.GithubIssuesResponse
import com.example.navigithubpr.data.Source.remote.RemoteDataSource

class DefaultPreRepository(remoteDataSource: PrDataSource):PrRepository {
    override suspend fun getTasks(forceUpdate: Boolean): Result<List<GithubIssuesResponse>> {
        TODO("Not yet implemented")
    }
}