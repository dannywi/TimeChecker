package com.example.timechecker.data;

import android.app.Activity;
import android.widget.Toast;

import com.example.timechecker.Const;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityBuilder {

    static List<AbstractEntity> entities = new ArrayList<>();
    public static Activity activity;

    static public void processHtml(String entityName, String html) {
        TransportationEntity entry = new TransportationEntity(entityName);
        ScrapperToBus scrapperToBus = new ScrapperToBus(html);

        for (DaySchedule sched : scrapperToBus.getSchedule()) {
            entry.addDaySchedule(sched.dayType, sched.scheduledTimes);
        }

        // need lock here
        entities.add(entry);

        Toast toast = Toast.makeText(activity, "Time table refreshed!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void cacheAllEntities() {
        //return Arrays.asList(TempBuilder.get96Otsu());
        AsyncURL async = new AsyncURL(Const.entity_1_name);
        async.execute(Const.entity_1_url);
    }

    static void addTimes(List<ScheduledTime> times, int hour, int[] minutes) {
        for (int minute : minutes)
            times.add(new ScheduledTime(hour, minute));
    }

    static public Map<DayType, List<Instant>> getApplicableTimesAllDays(Instant currentTime,
                                                                        String entityName) {
        for (AbstractEntity entity : entities) {
            if (entityName.equals(entity.getEntityName())) {
                return entity.getApplicableTimesAllDays(currentTime);
            }
        }
        throw new IllegalStateException("Entity not recognized");
    }
}
