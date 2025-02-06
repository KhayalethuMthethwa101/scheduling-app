package com.events_manager.model;
import com.events_manager.model.*;
import java.util.Date;

public class booking {
    private String bookingId;
    private visitor visitor;
    private event event;
    public Date bookingDate;

    public booking(Date bookingDate, visitor visitor, event event) {
        this.bookingDate = bookingDate;
        this.visitor = visitor;
        this.event = event;
    }

    public booking() {
    }

    public booking(event event, visitor visitor) {
        this.visitor = visitor;
        this.event = event;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(visitor visitor) {
        this.visitor = visitor;
    }

    public event getEvent() {
        return event;
    }

    public void setEvent(event event) {
        this.event = event;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
