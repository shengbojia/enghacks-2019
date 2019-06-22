package com.example.enghacks_2019.cache.db

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun calendarToTimestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun timestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}