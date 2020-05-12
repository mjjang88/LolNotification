package com.mjjang.lolnotification.data

import androidx.room.Entity
import androidx.room.PrimaryKey

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
}