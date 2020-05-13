package com.mjjang.lolnotification.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MatchDao {
    @Query("SELECT * FROM matchs ORDER BY CAST(id AS INTEGER) desc")
    fun getMatchList(): LiveData<List<Match>>

    @Query("SELECT * FROM matchs WHERE id = :matchId")
    fun getMatch(matchId: String): LiveData<Match>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(matchs: List<Match>)
}