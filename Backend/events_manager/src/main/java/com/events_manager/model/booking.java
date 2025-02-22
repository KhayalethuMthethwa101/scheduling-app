package com.events_manager.model;
import com.events_manager.model.*;
import com.google.cloud.Timestamp;

import java.util.Date;

public class booking {
    private String bookingId;
    private String email;
    private String eventId;
    private String eventName;
    private String timestamp;
    public String bookingDate;


    public booking(String bookingId, String email, String eventId, String eventName, String timestamp, String bookingDate) {
        this.bookingId = bookingId;
        this.email = email;
        this.eventId = eventId;
        this.eventName = eventName;
        this.timestamp = timestamp;
        this.bookingDate = bookingDate;
    }

    public booking() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
