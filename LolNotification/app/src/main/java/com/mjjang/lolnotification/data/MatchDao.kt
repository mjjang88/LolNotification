package com.mjjang.lolnotification.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MatchDao {
    @Query("SELECT * FROM matchs where Date(StartTime) <= Date(:today) ORDER BY CAST(id AS INTEGER) desc")
    fun getMatchList(today: String): LiveData<List<Match>>

    @Query("SELECT * FROM matchs WHERE id = :matchId")
    fun getMatch(matchId: String): LiveData<Match>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(matchs: List<Match>)

    @Update
    suspend fun updateOne(match: Match?)
}