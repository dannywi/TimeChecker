package com.example.timechecker.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EntityJsonPersister {
    static int FILE_VERSION = 1;

    static String KEY_FILE_VERSION = "FileVersion";
    static String KEY_ENTITY_CLASS = "EntityClass";
    static String KEY_NAME = "Name";
    static String KEY_SCHEDULES = "Schedules";
    static String KEY_DAY_TYPE = "DayType";
    static String KEY_TIMES = "Times";

    static String TIME_DELIM = ":";

    // { "FileVersion": 234, "EntityClass": "TransportationEntity", "Name": "I96", "Schedules": [ {"day": "weekday", "times": ["8:30", "9:30"] } ] }

    private static JSONObject convertToJson(AbstractEntity entity) throws JSONException {
        JSONObject jsonEntity = new JSONObject();
        jsonEntity.put(KEY_FILE_VERSION, FILE_VERSION);
        jsonEntity.put(KEY_ENTITY_CLASS, entity.getClass().getCanonicalName());
        jsonEntity.put(KEY_NAME, entity.getEntityName());

        JSONArray jsonSchedules = new JSONArray();
        for (DaySchedule ds : entity.getDaySchedules()) {
            JSONObject jsonDS = new JSONObject();
            jsonDS.put(KEY_DAY_TYPE, ds.dayType);

            JSONArray jsonTimes = new JSONArray();
            for (ScheduledTime st : ds.scheduledTimes)
                jsonTimes.put(String.format("%02d%s%02d", st.hour, TIME_DELIM, st.minutes));
            jsonDS.put(KEY_TIMES, jsonTimes);
            jsonSchedules.put(jsonDS);
        }
        jsonEntity.put(KEY_SCHEDULES, jsonSchedules);
        Log.d("Generated Json", jsonEntity.toString(2));
        return jsonEntity;
    }

    static String SUCCESS = "Persist succeeded";
    static String FAILURE = "Persist failed";
    static String FILENAME_FORMAT = "entity_%s.json";

    public static String persist(AbstractEntity entity, Activity activity) {
        try {
            JSONObject jsonEntity = convertToJson(entity);
            // todo: file save here
            // TODO: remove characters not good for filename
            //filename = "cache" + entity.getEntityName()

            String filename = String.format(FILENAME_FORMAT, entity.getEntityName());
            FileOutputStream outputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(jsonEntity.toString(2).getBytes());
            outputStream.close();

            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FAILURE;
    }

    public static AbstractEntity tryLoadingFromCache(String entityName, Activity activity) {
        String filename = String.format(FILENAME_FORMAT, entityName);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    activity.openFileInput(filename),
                    StandardCharsets.UTF_8));
            String lineBuffer;
            StringBuilder sb = new StringBuilder();
            while ((lineBuffer = reader.readLine()) != null) {
                sb.append(lineBuffer);
                Log.d("FileAccess", lineBuffer);
            }

            return entityFromJson(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static AbstractEntity entityFromJson(String text) {
        try {
            JSONObject obj = new JSONObject(text);
            if (obj.getInt(KEY_FILE_VERSION) != FILE_VERSION)
                throw new IllegalStateException("READ FAILURE: Unrecognized file version");

            String className = obj.getString(KEY_ENTITY_CLASS);
            String entityName = obj.getString(KEY_NAME);
            AbstractEntity entity = (AbstractEntity) Class.forName(className)
                    .getConstructor(String.class).newInstance(entityName);

            JSONArray jsonSchedules = obj.getJSONArray(KEY_SCHEDULES);
            for (int i = 0; i < jsonSchedules.length(); ++i) {
                JSONObject ds = jsonSchedules.getJSONObject(i);
                DayType dayType = DayType.valueOf(ds.getString(KEY_DAY_TYPE));
                List<ScheduledTime> times = new ArrayList<>();
                JSONArray jsonTimes = ds.getJSONArray(KEY_TIMES);
                for (int m = 0; m < jsonTimes.length(); ++m) {
                    String[] strs = jsonTimes.getString(m).split(TIME_DELIM);
                    if (strs.length != 2)
                        throw new IllegalStateException("INVALID TIME FORMAT");
                    times.add(new ScheduledTime(
                            Integer.valueOf(strs[0]), Integer.valueOf(strs[1])));
                }
                entity.addDaySchedule(dayType, times);
            }

            return entity;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
