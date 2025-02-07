package com.events_manager.model;
import com.google.type.DateTime;

import java.util.List;

public class event {
    private String eventId;
    public String eventName;
    public String eventDescription;
    public String location;
    public enum eventStatus {
        COMING,
        ACTIVE,
        ENDED;
    };
    public eventStatus status;
    public DateTime dateOfEvent;
    public List<visitor> attendees;

    public event(String eventName, String eventDescription, String location, eventStatus status, DateTime dateOfEvent) {
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

    public eventStatus getStatus() {
        return status;
    }

    public void setStatus(eventStatus status) {
        this.status = status;
    }

    public DateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(DateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public List<visitor> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<visitor> attendees) {
        this.attendees = attendees;
    }
}
