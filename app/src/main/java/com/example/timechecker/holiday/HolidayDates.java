package com.example.timechecker.holiday;

import android.app.Activity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class HolidayDates {
    public static List<Calendar> getList(Activity activity) {
        if (activity == null)
            return new ArrayList<>();

        // TODO: cache the structure from parsed file here, to save file IO
        return getFromCachedFile(activity);
    }

    private static String FILENAME = "holidays_jp.csv";
    private static String CSV_SOURCE = "https://www8.cao.go.jp/chosei/shukujitsu/syukujitsu.csv";

    private static List<Calendar> getFromCachedFile(Activity activity) {
        ensureLatestFile(activity);

        try {
            List<Calendar> results = new ArrayList<>();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    activity.openFileInput(FILENAME),
                    StandardCharsets.UTF_8));

            String lineBuffer;
            boolean firstSkipped = false; // field names

            while ((lineBuffer = reader.readLine()) != null) {
                if (!firstSkipped) {
                    firstSkipped = true;
                    continue;
                }

                String[] info = lineBuffer.split(",");
                SimpleDateFormat df = new SimpleDateFormat("yyyy/M/d", Locale.JAPANESE);
                Date date = df.parse(info[0]);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                results.add(cal);
            }

            return results;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void ensureLatestFile(Activity activity) {
        boolean existsAndRecent = false;
        StringBuilder sb = new StringBuilder();
        sb.append("holiday info");

        File file = new File(activity.getApplicationContext().getFilesDir(), FILENAME);
        if (file.exists()) {
            Calendar current = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
            long milliDiff = current.getTimeInMillis() - file.lastModified();
            long daysDiff = milliDiff / (24 * 60 * 60 * 1000);

            sb.append(String.format(" - %s days old", daysDiff));

            // reload if older than 6 months
            existsAndRecent = daysDiff < 30 * 6;
        }

        if (!existsAndRecent) {
            AsyncURLHoliday async = new AsyncURLHoliday(activity, FILENAME);
            async.execute(CSV_SOURCE);
            sb.append(" - try download");
        }

        Toast toast = Toast.makeText(activity, sb.toString(), Toast.LENGTH_SHORT);
        toast.show();
    }
}
