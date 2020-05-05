package com.mjjang.lolnotification.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "matchs")
data class Match(
    @PrimaryKey val id: String,
    val TeamA: String,
    val TeamB: String,
    val StartTime: Calendar
) {

    override fun toString(): String {
        return "$TeamA vs $TeamB"
    }
}