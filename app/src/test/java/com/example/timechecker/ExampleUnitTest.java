package com.example.timechecker;

import com.example.timechecker.data.DayTypeChecker;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void dayOfWeek() {
        DayTypeChecker d = new DayTypeChecker();
        System.out.println(d.getCurrentDayType(null));
    }

    @Test
    public void testCsvDownload() {
        try {
            URL url = new URL("https://www8.cao.go.jp/chosei/shukujitsu/syukujitsu.csv");
            URLConnection connection = url.openConnection();

            InputStreamReader input = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader buffer = null;
            String line = "";
            String csvSplitBy = ",";

            buffer = new BufferedReader(input);
            boolean firstSkipped = false;
            while ((line = buffer.readLine()) != null) {
                if (!firstSkipped) {
                    firstSkipped = true;
                    continue;
                }

                String[] info = line.split(csvSplitBy);
                //System.out.println(line);
                System.out.printf("[%s] [%s]\n", info[0], info[1]);

                SimpleDateFormat df = new SimpleDateFormat("yyyy/M/d", Locale.JAPANESE);
                Date date = df.parse(info[0]);
                System.out.println(date);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

                System.out.println(" youbi - " + cal.get(Calendar.DAY_OF_WEEK) + " -- " + df2.format(cal.getTimeInMillis()));

                long milli1 = cal.getTimeInMillis();
                if (milli1 > 0) {
                    long diff = Calendar.getInstance().getTimeInMillis() - milli1;
                    //System.out.println(" from today - " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                    System.out.println(" from today - " + diff / (24 * 60 * 60 * 1000));
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}