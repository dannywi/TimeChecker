package com.example.timechecker.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ScrapperToBus {
    private String html;

    public ScrapperToBus(String html) {
        this.html = html;
    }

    void addIfExists(List<DaySchedule> list, DayType dayType, String dayTableClass) {
        DaySchedule ds = getDaySchedule(dayType, dayTableClass);
        if (ds.scheduledTimes.size() > 0)
            list.add(ds);
    }

    public List<DaySchedule> getSchedule() {
        // TODO: don't need to access website everytime, maybe just once a day.
        // find out how to save this info in device, and only access if file
        // is > a day old. but failback to that file if internet is not working.
        // maybe toast a message on update.

        List<DaySchedule> ret = new ArrayList<>();
        addIfExists(ret, DayType.WEEKDAY, "timeTableWkd");
        addIfExists(ret, DayType.SATURDAY, "timeTableSat");
        addIfExists(ret, DayType.SUNDAYHOLIDAY, "timeTableHld");

        return ret;
    }

    private DaySchedule getDaySchedule(DayType dayType, String dayTableClass) {
        List<ScheduledTime> times = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select("table[class=" + dayTableClass + "]");
            Element element = elements.first();
            Elements rows = element.select("th[scope=row]");

            for (Element hour : rows) {
                for (Element minute : hour.nextElementSiblings()) {
                    if (minute.text().length() == 0)
                        continue;
                    times.add(
                            new ScheduledTime(
                                    Integer.valueOf(hour.text()),
                                    Integer.valueOf(minute.text())
                            ));
                }
            }
        } catch (Exception e) {
            System.out.println("ERR ...\n" + e.toString());
        }
        return new DaySchedule(dayType, times);
    }
}
