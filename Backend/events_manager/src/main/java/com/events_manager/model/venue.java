package com.events_manager.model;

public class venue {
    private String venueId;
    public String venueName;
    public String venueLocation;
    public int capacity;

    public venue(String venueId, String venueName, String venueLocation, int capacity) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.venueLocation = venueLocation;
        this.capacity = capacity;
    }

    public venue(String venueName, String venueLocation, String venueId) {
        this.venueName = venueName;
        this.venueLocation = venueLocation;
        this.venueId = venueId;
    }

    public venue() {
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueLocation() {
        return venueLocation;
    }

    public void setVenueLocation(String venueLocation) {
        this.venueLocation = venueLocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
