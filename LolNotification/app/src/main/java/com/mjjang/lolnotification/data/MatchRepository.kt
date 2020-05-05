package com.mjjang.lolnotification.data

import androidx.lifecycle.LiveData


class MatchRepository private constructor(
    private val matchDao: MatchDao
) {
    fun getMatchList() : LiveData<List<Match>> {
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
}