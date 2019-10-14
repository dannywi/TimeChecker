package com.example.timechecker.data;

class ScheduledTime {
    // since midnight local
    int hour;
    int minutes;

    ScheduledTime(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }
}
