package com.mjjang.lolnotification.data

import androidx.lifecycle.LiveData
import com.mjjang.lolnotification.network.LolNotiDBRetrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MatchRepository private constructor(
    private val matchDao: MatchDao
) {
    fun getMatchList() : LiveData<List<Match>> {
        refreshMatch()
        return matchDao.getMatchList()
    }

    fun getMatch(matchId: String) = matchDao.getMatch(matchId)

    companion object {
        @Volatile private var instance: MatchRepository? = null

        fun getInstance(matchDao: MatchDao) =
            instance ?: synchronized(this) {
                instance ?: MatchRepository(matchDao).also { instance = it }
            }
    }

    private fun refreshMatch() {
        LolNotiDBRetrofit.getService().requestMatch().enqueue(object : Callback<List<Match>> {
            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    GlobalScope.launch {
                        response.body()?.let { matchDao.insertAll(it) }
                    }
                }
            }
        })
    }
}