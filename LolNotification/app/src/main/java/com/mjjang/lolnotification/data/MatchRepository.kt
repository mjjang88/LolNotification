package com.mjjang.lolnotification.data

import android.os.Build
import androidx.lifecycle.LiveData
import com.mjjang.lolnotification.network.LolNotiDBRetrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class MatchRepository private constructor(
    private val matchDao: MatchDao
) {
    fun getMatchList() : LiveData<List<Match>> {
        refreshMatch()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //matchDao.getMatchList(LocalDate.now().toString())
            //matchDao.getMatchList(LocalDate.now().plusMonths(1).toString())
            if (LocalDate.now().toString() >= "2020-06-17") {
                matchDao.getMatchList(LocalDate.now().toString())
            } else {
                matchDao.getMatchList("2020-06-17")
            }
        } else {
            val dateString = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis()))
            if (dateString >= "2020-06-17") {
                matchDao.getMatchList(dateString)
            } else {
                matchDao.getMatchList("2020-06-17")
            }
        }
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