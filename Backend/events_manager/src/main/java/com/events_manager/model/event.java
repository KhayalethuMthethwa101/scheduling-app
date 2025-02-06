package com.events_manager.model;

import com.google.type.DateTime;

import java.util.List;

public class event {
    private String eventId;
    public String eventName;
    public String eventDescription;
    public String catagory;
    public venue venue;
    public enum eventStatus {
        COMING,
        ACTIVE,
        ENDED;
    };
    public eventStatus status;
    public DateTime dateOfEvent;
    public List<visitor> attendees;

    public event(String eventName, String eventDescription, String catagory, com.events_manager.model.venue venue, eventStatus status, DateTime dateOfEvent) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.catagory = catagory;
        this.venue = venue;
        this.status = status;
        this.dateOfEvent = dateOfEvent;
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

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public venue getVenue() {
        return venue;
    }

    public void setVenue(venue venue) {
        this.venue = venue;
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
