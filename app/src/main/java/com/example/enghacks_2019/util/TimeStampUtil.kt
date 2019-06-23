package com.example.enghacks_2019.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun convertDateToString(date: Date): String {

    val currentTime = Calendar.getInstance()

    val dateFormat = when (dateRelativeToCalendar(date, currentTime)) {
        DateStatus.NOTHING -> DateFormat.getDateInstance(DateFormat.LONG)
        DateStatus.SAME_YEAR -> DateRegexUtil.removeYears(DateFormat.getDateInstance(DateFormat.LONG))
        DateStatus.SAME_WEEK -> SimpleDateFormat("EEEE", Locale.getDefault())
    }

    val dateText = "${dateFormat.format(date)} - ${DateFormat.getTimeInstance(DateFormat.SHORT).format(date)}"

   return dateText

}

/**
 * Enum class for the different possible relations between two [Date]s.
 */
private enum class DateStatus {
    SAME_WEEK,
    SAME_YEAR,
    NOTHING
}

/**
 * Determines whether a [Date] and a [Calendar] are within the same year, week, or neither.
 */
private fun dateRelativeToCalendar(date: Date, calendar: Calendar): DateStatus {
    val week = calendar.get(Calendar.WEEK_OF_YEAR)
    val year = calendar.get(Calendar.YEAR)

    val tempCalendar = Calendar.getInstance()
    tempCalendar.time = date

    val tempWeek = tempCalendar.get(Calendar.WEEK_OF_YEAR)
    val tempYear = tempCalendar.get(Calendar.YEAR)

    if (year != tempYear) {
        return DateStatus.NOTHING
    } else if (week != tempWeek) {
        return DateStatus.SAME_YEAR
    } else {
        return DateStatus.SAME_WEEK
    }
}