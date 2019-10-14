package com.example.timechecker.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityBuilder {

    public static List<AbstractEntity> getAllEntities() {
        // TODO: hacked. upgrade to file or database lookup from web source
        TransportationEntity I96ShinagawaEki = new TransportationEntity("I96ShinagawaEki");

        {
            List<ScheduledTime> times = new ArrayList<>();
            // WEEKDAYS
            {
                int hour = 7;
                int[] minutes = new int[]{0, 15, 27, 36, 45, 53};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 8;
                int[] minutes = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 9;
                int[] minutes = new int[]{0, 5, 10, 15, 20, 25, 30, 36, 43, 50, 56};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 10;
                int[] minutes = new int[]{2, 8, 15, 22, 29, 36, 43, 50, 56};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 11;
                int[] minutes = new int[]{4, 11, 18, 25, 32, 39, 46, 53};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 12;
                int[] minutes = new int[]{0, 8, 16, 24, 31, 38, 45, 53};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 13;
                int[] minutes = new int[]{0, 8, 16, 25, 34, 43, 53};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 14;
                int[] minutes = new int[]{2, 12, 22, 32, 42, 52};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 15;
                int[] minutes = new int[]{1, 9, 17, 25, 32, 38, 45, 52, 59};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 16;
                int[] minutes = new int[]{5, 11, 16, 21, 26, 31, 36, 41, 46, 51, 56};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 17;
                int[] minutes = new int[]{1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 51, 56};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 18;
                int[] minutes = new int[]{1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 52, 58};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 19;
                int[] minutes = new int[]{4, 10, 16, 22, 28, 35, 42, 49, 56};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 20;
                int[] minutes = new int[]{4, 13, 23, 33, 45, 59};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 21;
                int[] minutes = new int[]{12, 28, 43, 58};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 22;
                int[] minutes = new int[]{14};
                addTimes(times, hour, minutes);
            }
            I96ShinagawaEki.addDaySchedule(DayType.WEEKDAY, times);
        }

        {
            List<ScheduledTime> times = new ArrayList<>();
            // SATURDAY
            {
                int hour = 7;
                int[] minutes = new int[]{0, 30};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 8;
                int[] minutes = new int[]{0, 20, 35, 50};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 9;
                int[] minutes = new int[]{5, 20, 35, 50};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 10;
                int[] minutes = new int[]{5, 20, 35};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 11;
                int[] minutes = new int[]{0, 30};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 12;
                int[] minutes = new int[]{0, 30};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 13;
                int[] minutes = new int[]{0, 30};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 14;
                int[] minutes = new int[]{0, 30};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 15;
                int[] minutes = new int[]{0, 18, 34, 49};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 16;
                int[] minutes = new int[]{5, 20, 33, 47};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 17;
                int[] minutes = new int[]{0, 14, 29, 47};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 18;
                int[] minutes = new int[]{14, 40};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 19;
                int[] minutes = new int[]{10, 40};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 20;
                int[] minutes = new int[]{10, 40};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 21;
                int[] minutes = new int[]{10, 40};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 22;
                int[] minutes = new int[]{10};
                addTimes(times, hour, minutes);
            }
            I96ShinagawaEki.addDaySchedule(DayType.SATURDAY, times);
        }

        {
            List<ScheduledTime> times = new ArrayList<>();
            // SUNDAY HOLIDAY
            {
                int hour = 7;
                int[] minutes = new int[]{0, 30};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 8;
                int[] minutes = new int[]{0, 30};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 9;
                int[] minutes = new int[]{0, 30, 57};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 10;
                int[] minutes = new int[]{24, 51};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 11;
                int[] minutes = new int[]{6, 22, 37, 52};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 12;
                int[] minutes = new int[]{6, 22, 37, 52};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 13;
                int[] minutes = new int[]{6, 22, 37, 52};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 14;
                int[] minutes = new int[]{6, 22, 37, 52};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 15;
                int[] minutes = new int[]{6, 21, 36, 51};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 16;
                int[] minutes = new int[]{6, 21, 36, 51};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 17;
                int[] minutes = new int[]{6, 33};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 18;
                int[] minutes = new int[]{0, 26, 56};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 19;
                int[] minutes = new int[]{26, 56};
                addTimes(times, hour, minutes);
            }
            {
                int hour = 20;
                int[] minutes = new int[]{26, 54};
                addTimes(times, hour, minutes);
            }
            I96ShinagawaEki.addDaySchedule(DayType.SUNDAYHOLIDAY, times);
        }

        return Arrays.asList((AbstractEntity) I96ShinagawaEki);
    }

    static void addTimes(List<ScheduledTime> times, int hour, int[] minutes) {
        for (int minute : minutes)
            times.add(new ScheduledTime(hour, minute));
    }
}
