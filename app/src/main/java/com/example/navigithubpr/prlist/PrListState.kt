package com.example.navigithubpr.prlist

import com.example.navigithubpr.data.response.GithubIssuesResponse


data class PrListState(

    val isLoading:Boolean = false,
    val isLoadingError:Boolean = false,
    val error:String?  = null,
    val list:List<GithubIssuesResponse>? = emptyList()

)