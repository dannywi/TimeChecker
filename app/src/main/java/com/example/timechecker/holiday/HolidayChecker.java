package com.example.timechecker.holiday;

import android.app.Activity;

import java.util.Calendar;
import java.util.List;

public class HolidayChecker {

    public static boolean isHoliday(Calendar instance, Activity activity) {
        List<Calendar> holidays = HolidayDates.getList(activity);

        if (isHolidayItself(instance, holidays))
            return true;

        if (isMondayAfterSundayHoliday(instance, holidays))
            return true;

        return false;
    }

    private static boolean isHolidayItself(Calendar cal, List<Calendar> holidays) {
        for (Calendar c : holidays) {
            if (isSameDate(c, cal))
                return true;
        }
        return false;
    }

    private static boolean isMondayAfterSundayHoliday(Calendar cal, List<Calendar> holidays) {
        if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
            return false;

        Calendar yesterday = (Calendar) cal.clone();
        yesterday.add(Calendar.DATE, -1);
        return isHolidayItself(yesterday, holidays);
    }

    private static boolean isSameDate(Calendar c1, Calendar c2) {
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR))
            return false;
        if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH))
            return false;
        if (c1.get(Calendar.DATE) != c2.get(Calendar.DATE))
            return false;
        return true;
    }
}
