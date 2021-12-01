package com.example.navigithubpr.data.Source

import androidx.lifecycle.LiveData
import com.example.navigithubpr.data.Result
import com.example.navigithubpr.data.GithubIssuesResponse

interface PrRepository {

    suspend fun getTasks(orgName:String, folderName:String): Result<List<GithubIssuesResponse>>

}
