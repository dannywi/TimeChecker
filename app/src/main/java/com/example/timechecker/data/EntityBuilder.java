package com.example.timechecker.data;

import android.app.Activity;
import android.widget.Toast;

import com.example.timechecker.Const;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityBuilder {

    static Map<String, AbstractEntity> entities = new HashMap<>();
    public static Activity activity;

    static void addEntity(AbstractEntity entity) {
        // need lock here
        entities.put(entity.getEntityName(), entity);
    }

    static boolean isEntityValid(AbstractEntity entity) {
        if (entity.getDaySchedules().size() == 0)
            return false;
        for (DaySchedule ds : entity.getDaySchedules()) {
            if (ds.scheduledTimes.size() == 0)
                return false;
        }
        return true;
    }

    static void processHtml(String entityName, String html) {
        // TODO: remove activity static reference, make this function a closure
        TransportationEntity entry = new TransportationEntity(entityName);
        ScrapperToBus scrapperToBus = new ScrapperToBus(html);

        for (DaySchedule sched : scrapperToBus.getSchedule())
            entry.addDaySchedule(sched.dayType, sched.scheduledTimes);

        String message;
        if (isEntityValid(entry)) {
            addEntity(entry);
            String res = EntityJsonPersister.persist(entry, activity);
            message = "Time table refreshed - " + res;
        } else {
            message = "Time table not refreshed - FAILURE";
        }

        Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void downloadEntityInfo(String entityName) {
        AsyncURLEntity async = new AsyncURLEntity(entityName);
        async.execute(Const.getUrl(entityName));
    }

    public static void cacheAllEntities() {
        //return Arrays.asList(TempBuilder.get96Otsu());
        // TODO: make this option list
        String entityName = Const.ENTITY_1_NAME;
        AbstractEntity cached = EntityJsonPersister.tryLoadingFromCache(entityName, activity);
        if (cached != null && isEntityValid(cached)) {
            addEntity(cached);
            Toast toast = Toast.makeText(activity, "Loaded times from cache", Toast.LENGTH_SHORT);
            toast.show();
        } else
            downloadEntityInfo(entityName);
    }

    static void addTimes(List<ScheduledTime> times, int hour, int[] minutes) {
        for (int minute : minutes)
            times.add(new ScheduledTime(hour, minute));
    }

    static public Map<DayType, List<Instant>>
    getApplicableTimesAllDays(Instant currentTime, String entityName) {
        if (!entities.containsKey(entityName))
            throw new IllegalStateException("Entity not recognized");
        return entities.get(entityName).getApplicableTimesAllDays(currentTime);
    }
}
