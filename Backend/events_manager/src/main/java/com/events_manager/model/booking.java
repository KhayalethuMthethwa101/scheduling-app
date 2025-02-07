package com.events_manager.model;
import com.events_manager.model.*;
import java.util.Date;

public class booking {
    private String bookingId;
    private String userId;
    private String eventId;
    public Date bookingDate;

    public booking(String bookingId, String userId, String eventId, Date bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
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
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
