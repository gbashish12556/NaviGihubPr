package com.example.navigithubpr.data.Source.remote

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.GithubIssuesResponse
import com.example.navigithubpr.data.Source.PrDataSource

class RemoteDataSource(application: Application):PrDataSource {

    override suspend fun getTasks(): Result<List<GithubIssuesResponse>> {
        TODO("Not yet implemented")
    }

}