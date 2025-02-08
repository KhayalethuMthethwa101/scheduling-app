package com.events_manager.util;
import com.google.cloud.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimestampConverter {

    public static LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return timestamp.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Timestamp convertToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.of(java.sql.Timestamp.valueOf(localDateTime));
    }
}

