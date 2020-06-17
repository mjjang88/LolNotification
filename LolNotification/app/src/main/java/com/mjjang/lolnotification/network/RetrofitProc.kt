package com.mjjang.lolnotification.network

import com.mjjang.lolnotification.data.AppDatabase
import com.mjjang.lolnotification.data.Match
import com.mjjang.lolnotification.manager.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RetrofitProc {

    fun incRecomMatch(strId: String?) {
        LolNotiDBRetrofit.getService().increaseRecomCount(strId).enqueue(CallbackRefreshMatch(strId))
    }

    fun decRecomMatch(strId: String?) {
        LolNotiDBRetrofit.getService().decreaseRecomCount(strId).enqueue(CallbackRefreshMatch(strId))
    }

    fun incNotRecomMatch(strId: String?) {
        LolNotiDBRetrofit.getService().increaseNotRecomCount(strId).enqueue(CallbackRefreshMatch(strId))
    }

    fun decNotRecomMatch(strId: String?) {
        LolNotiDBRetrofit.getService().decreaseNotRecomCount(strId).enqueue(CallbackRefreshMatch(strId))
    }

    fun refreshMatch() {
        LolNotiDBRetrofit.getService().requestMatch().enqueue(object : Callback<List<Match>> {
            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    GlobalScope.launch {
                        response.body()?.let {
                            val database = AppDatabase.getInstance(App.applicationContext())
                            response.body()?.let { database.matchDao().insertAll(it) }
                        }
                    }
                }
            }
        })
    }

    private fun updateMatch(strID: String?) {
        LolNotiDBRetrofit.getService().requestMatchById(strID).enqueue(object : Callback<Match> {
            override fun onFailure(call: Call<Match>, t: Throwable) {
            }

            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                if (response.isSuccessful) {
                    GlobalScope.launch {
                        response.body()?.let {
                            val database = AppDatabase.getInstance(App.applicationContext())
                            database.matchDao().updateOne(it)
                        }
                    }
                }
            }
        })
    }

    class CallbackRefreshMatch(private val strId: String?) : Callback<Match> {
        override fun onFailure(call: Call<Match>, t: Throwable) {
        }

        override fun onResponse(call: Call<Match>, response: Response<Match>) {
            if (response.isSuccessful) {
                GlobalScope.launch {
                    response.body()?.let {
                        val database = AppDatabase.getInstance(App.applicationContext())
                        database.matchDao().updateOne(it)
                    }
                }
            }
        }
    }
}