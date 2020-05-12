package com.mjjang.lolnotification.network

import com.mjjang.lolnotification.data.Match
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/match")
    fun requestMatch(
    ): Call<List<Match>>
}