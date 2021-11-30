package com.example.navigithubpr.data.Source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteNetworkingClient private constructor() {
    private val retrofit: Retrofit
    val api: Api
        get() = retrofit.create(Api::class.java)

    companion object {
        const val BASE_URL = "https://api.github.com"
        private var mRetrofitInstance: RemoteNetworkingClient? = null

        @get:Synchronized
        val instance: RemoteNetworkingClient?
            get() {
                if (mRetrofitInstance == null) {
                    mRetrofitInstance = RemoteNetworkingClient()
                }
                return mRetrofitInstance
            }
    }

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}