package com.ur4n0.schoolroom.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeConverter {
    public static LocalDateTime convertTimestampToDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zoneId = ZoneId.systemDefault(); 
        return LocalDateTime.ofInstant(instant, zoneId);
    }
}
