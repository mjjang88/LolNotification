package com.mjjang.lolnotification.network

import com.mjjang.lolnotification.data.Match
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("/match")
    fun requestMatch(
    ): Call<List<Match>>

    @GET("/match/matchid/{matchId}")
    fun requestMatchById(@Path("matchId") matchId: String?
    ): Call<Match>

    @GET("/match/matchid/{matchId}/increcom")
    fun increaseRecomCount(@Path("matchId") matchId : String?
    ): Call<Match>

    @GET("/match/matchid/{matchId}/decrecom")
    fun decreaseRecomCount(@Path("matchId") matchId : String?
    ): Call<Match>

    @GET("/match/matchid/{matchId}/incnotrecom")
    fun increaseNotRecomCount(@Path("matchId") matchId : String?
    ): Call<Match>

    @GET("/match/matchid/{matchId}/decnotrecom")
    fun decreaseNotRecomCount(@Path("matchId") matchId : String?
    ): Call<Match>

    @FormUrlEncoded
    @PATCH("/match/matchid/{matchId}")
    fun updateMatchData(@Path("matchId") matchId: String?, @Field("YouTubeLink") youtubeLink: String?, @Field("NaverLink") naverLink: String?, @Field("EditorRecommend") EditorRecommend: Int
    ): Call<Match>
}