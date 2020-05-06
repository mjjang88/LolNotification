package com.mjjang.lolnotification.data

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

    @TypeConverter
    fun calendarToString(calendar: Calendar): String = calendar.toString()

    @TypeConverter
    fun stringToCalendar(value: String): Calendar {
        val calendar = Calendar.getInstance()
        val jsonDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        calendar.time = jsonDateFormat.parse(value)
        return calendar
    }
}