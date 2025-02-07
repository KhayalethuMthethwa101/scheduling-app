package com.events_manager.model;
import com.google.type.DateTime;

import java.time.LocalDateTime;
import java.util.List;

public class event {
    private String eventId;
    public String eventName;
    public String eventDescription;
    public String location;
    public String status;
    public LocalDateTime dateOfEvent;
    public List<user> attendees;

    public event(String eventName, String eventDescription, String location, String status, LocalDateTime dateOfEvent) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.location = location;
        this.status = status;
        this.dateOfEvent = dateOfEvent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(LocalDateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public List<user> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<user> attendees) {
        this.attendees = attendees;
    }
}
