package com.example.enghacks_2019.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateRegexUtil {
    /**
     * Parses a DateFormat.LONG style DateFormat and removes the year part.
     *
     * @return DateFormat without year
     */
    public static DateFormat removeYears(DateFormat dateFormat) {
        try {
            SimpleDateFormat sdf = (SimpleDateFormat) dateFormat;
            sdf.applyPattern(sdf.toPattern()
                    .replaceAll(
                            "([^\\p{Alpha}']|('[\\p{Alpha}]+'))*y+([^\\p{Alpha}']|('[\\p{Alpha}]+'))*",
                            "")
                    .replaceAll(
                            "年*",
                            ""));
            return sdf;
        } catch (Exception ex) {
            return dateFormat;
        }

    }
}
