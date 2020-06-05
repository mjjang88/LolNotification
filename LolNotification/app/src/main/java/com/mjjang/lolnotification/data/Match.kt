package com.mjjang.lolnotification.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "matchs")
data class Match(
    @PrimaryKey val id: String,
    val TeamA: String,
    val TeamB: String,
    val GameNumber: String,
    val StartTime: String,
    val RecommendCount: String,
    val NotRecommendCount: String,
    val YouTubeLink: String?,
    val NaverLink: String?
) {

    override fun toString(): String {
        return "$TeamA vs $TeamB"
    }

    fun gameNumberToString(): String {
        return "$GameNumber 세트"
    }

    fun startTimeToString(): String {
        val calendar = Calendar.getInstance()
        calendar.time = DATE_FORMAT.parse(StartTime)

        return "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DATE)} ${calendar.get(Calendar.HOUR)}시"
    }

    companion object {
        private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    }
}