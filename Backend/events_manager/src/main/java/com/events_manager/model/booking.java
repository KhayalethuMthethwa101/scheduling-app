package com.events_manager.model;
import com.events_manager.model.*;
import com.google.cloud.Timestamp;

import java.util.Date;

public class booking {
    private String bookingId;
    private String email;
    private String eventId;
    public String bookingDate;

    public booking(String bookingId, String email, String eventId, String bookingDate) {
        this.bookingId = bookingId;
        this.email = email;
        this.eventId = eventId;
        this.bookingDate = bookingDate;
    }

    public booking() {
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return email;
    }

    public void setUserId(String email) {
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
