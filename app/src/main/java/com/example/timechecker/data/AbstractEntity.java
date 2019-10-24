package com.example.timechecker.data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public abstract class AbstractEntity {
    String name;

    public AbstractEntity(String name) {
        this.name = name;
    }

    List<Instant> getApplicableTimes(Instant currentTime) {
        return null;
    }

    // TODO: no need for this if we can infer applicable one automatically
    Map<DayType, List<Instant>> getApplicableTimesAllDays(Instant currentTime) {
        return null;
    }

    abstract void addDaySchedule(DayType dayType, List<ScheduledTime> times);

    abstract List<DaySchedule> getDaySchedules();

    String getEntityName() {
        return name;
    }
}
