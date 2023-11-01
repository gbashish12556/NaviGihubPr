package com.example.navigithubpr.prlist

import com.example.navigithubpr.data.response.GithubIssuesResponse


data class PrListState(

    val isLoading:Boolean = false,
    val error:String?  = null,
    val coins:List<GithubIssuesResponse>? = emptyList()

)