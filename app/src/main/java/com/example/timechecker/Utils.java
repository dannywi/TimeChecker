package com.example.timechecker;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

class Utils {
    static String convertToTimeAndDistance(List<Instant> instances, Instant currentTime) {
        StringBuilder sb = new StringBuilder();

        for (Instant instant : instances) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                ZonedDateTime zdt = instant.atZone(ZoneId.of("Asia/Tokyo"));
                sb.append(String.format("%02d:%02d\n", zdt.getHour(), zdt.getMinute()));
            }
        }

        return sb.toString();
    }
}
