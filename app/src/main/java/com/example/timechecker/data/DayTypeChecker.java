package com.example.timechecker.data;

import java.util.Calendar;
import java.util.TimeZone;

public class DayTypeChecker {

    public DayType getCurrentDayType() {
        // TODO: pass time zone
        Calendar local = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));

        int dayOfWeek = local.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY) {
            return DayType.SUNDAYHOLIDAY;
        } else if (dayOfWeek == Calendar.SATURDAY) {
            // TODO: Saturday can be a holiday, fix this when we can get holidays
            return DayType.SATURDAY;
        }
        return DayType.WEEKDAY;
    }


}
