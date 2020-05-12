package com.mjjang.lolnotification.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LolNotiDBRetrofit {
    fun getService(): RetrofitService = retrofit.create(RetrofitService::class.java)

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://lol-notification-db.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}