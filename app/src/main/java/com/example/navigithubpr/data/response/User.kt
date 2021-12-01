package com.example.navigithubpr.data.response
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

data class User(
    @SerializedName("login")
    var login: String?,

    @SerializedName("id")
    var id: Long?,

    @SerializedName("avatar_url")
    var avatarUrl: String?

)