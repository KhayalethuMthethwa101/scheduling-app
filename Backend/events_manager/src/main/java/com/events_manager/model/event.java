package com.events_manager.model;

import com.google.cloud.Timestamp;
import java.util.List;

public class event {
    private String eventId;
    public String eventName;
    public String eventDescription;
    public String location;
    public String latitude;
    public String longitude;
    public String status;
    public String dateOfEvent;
    public String timeOfEvent;
    public String category;
    public int capacity;
    public String fee;
    public String imageUrl;


    public event(String eventId, String eventName, String eventDescription, String location, String status, String latitude, String longitude, String dateOfEvent, String timeOfEvent, String imageUrl,String category, int capacity, String fee) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.dateOfEvent = dateOfEvent;
        this.timeOfEvent = timeOfEvent;
        this.category = category;
        this.capacity = capacity;
        this.imageUrl = imageUrl;
        this.fee = fee;
    }

    public event(){

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(String timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
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

    public String getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

}
