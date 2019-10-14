package com.example.timechecker.data;

import java.util.List;

class DaySchedule {
    DayType dayType;
    List<ScheduledTime> scheduledTimes;

    DaySchedule(DayType dayType, List<ScheduledTime> scheduledTimes) {
        this.dayType = dayType;
        this.scheduledTimes = scheduledTimes;
    }
}