package com.example.timechecker.data;

import android.app.Activity;

import com.example.timechecker.holiday.HolidayChecker;

import java.util.Calendar;
import java.util.TimeZone;

public class DayTypeChecker {

    public DayType getCurrentDayType(Activity activity) {
        // TODO: pass time zone
        Calendar local = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));

        int dayOfWeek = local.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SUNDAY)
            return DayType.SUNDAYHOLIDAY;

        if (HolidayChecker.isHoliday(local, activity))
            return DayType.SUNDAYHOLIDAY;

        if (dayOfWeek == Calendar.SATURDAY) {
            return DayType.SATURDAY;
        }

        return DayType.WEEKDAY;
    }


}
