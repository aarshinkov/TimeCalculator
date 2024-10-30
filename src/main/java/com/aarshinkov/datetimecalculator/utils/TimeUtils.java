package com.aarshinkov.datetimecalculator.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.2.0
 */
public class TimeUtils {

    public static Timestamp getNowTimeWithZoneOffset(ZoneOffset zoneOffset) {
        Instant now = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, zoneOffset);
        return Timestamp.valueOf(localDateTime);
    }

    public static Timestamp getNowTimeUTC() {
        Instant now = Instant.now();
        LocalDateTime localDateTimeUtc = LocalDateTime.ofInstant(now, ZoneOffset.UTC);
        return Timestamp.valueOf(localDateTimeUtc);
    }
}
