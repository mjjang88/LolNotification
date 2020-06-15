package com.mjjang.lolnotification.network

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.mjjang.lolnotification.R
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

    fun updateMatchData(strId: String?, youtubeLink: String?, naverLink: String?, recom: String?) {
        var nRecom : Int = 0
        if (!recom.isNullOrBlank()) {
            nRecom = recom.toInt()
        }
        LolNotiDBRetrofit.getService().updateMatchData(strId, youtubeLink, naverLink, nRecom).enqueue(object : Callback<Match> {
            override fun onFailure(call: Call<Match>, t: Throwable) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(App.applicationContext(), R.string.fail_message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                if (response.isSuccessful) {
                    GlobalScope.launch {
                        response.body()?.let {
                            val database = AppDatabase.getInstance(App.applicationContext())
                            database.matchDao().updateOne(it)
                            Handler(Looper.getMainLooper()).post {
                                Toast.makeText(App.applicationContext(), R.string.accept_message, Toast.LENGTH_SHORT).show()
                            }
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