package com.example.navigithubpr.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubIssuesResponse(
    @SerializedName("id")
    var id: Long?,
    @SerializedName("user")
    var user: User?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("closed_at")
    var closedAt: String?
):Serializable