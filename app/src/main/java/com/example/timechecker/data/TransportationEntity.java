package com.example.timechecker.data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportationEntity extends AbstractEntity {
    List<DaySchedule> daySchedules = new ArrayList<>();
    String name;

    private static long SECS_IN_MINUTE = 60;
    private static long SECS_IN_HOUR = SECS_IN_MINUTE * 60;

    TransportationEntity(String name) {
        this.name = name;
    }

    @Override
    public String getEntityName() {
        return name;
    }

    @Override
    public Map<DayType, List<Instant>> getApplicableTimesAllDays(Instant currentTime) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Map<DayType, List<Instant>> res = new HashMap<>();

            long epochMidnightSeconds = getEpochMidnightSeconds();
            for (DaySchedule ds : daySchedules) {
                List<Instant> times = new ArrayList<>();
                for (ScheduledTime st : ds.scheduledTimes) {
                    long scheduledTimeEpochSeconds = epochMidnightSeconds
                            + st.hour * SECS_IN_HOUR
                            + st.minutes * SECS_IN_MINUTE;
                    if (currentTime.getEpochSecond() < scheduledTimeEpochSeconds) {
                        times.add(Instant.ofEpochSecond(scheduledTimeEpochSeconds));
                    }
                }
                res.put(ds.dayType, times);
            }

            return res;
        }
        throw new IllegalStateException("Cannot get applicable times all days");
    }

    public void addDaySchedule(DayType dayType, List<ScheduledTime> times) {
        daySchedules.add(new DaySchedule(dayType, times));
    }

    static long getEpochMidnightSeconds() {
        // TODO: support localization / other timezone
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ZoneId zoneId = ZoneId.of("Asia/Tokyo");
            ZonedDateTime now = ZonedDateTime.now(zoneId);
            ZonedDateTime todayStart = now.toLocalDate().atStartOfDay(zoneId);
            return todayStart.toEpochSecond();
        }
        throw new IllegalStateException("Cannot get current time");
    }
}
