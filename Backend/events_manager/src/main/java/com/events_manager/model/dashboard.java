package com.events_manager.model;

import java.util.List;
import java.util.Map;

public class dashboard {
    private String dashboardId;
    public event event;
    public List<visitorDemographics> visitorDemographics;
    public Map<event, Integer> eventAttendemce;
    public Map<event, Float> eventRatings;
    public List<String> eventComments;

    public dashboard(String dashboardId, com.events_manager.model.event event, List<com.events_manager.model.visitorDemographics> visitorDemographics, Map<com.events_manager.model.event, Integer> eventAttendemce, Map<com.events_manager.model.event, Float> eventRatings, List<String> eventComments) {
        this.dashboardId = dashboardId;
        this.event = event;
        this.visitorDemographics = visitorDemographics;
        this.eventAttendemce = eventAttendemce;
        this.eventRatings = eventRatings;
        this.eventComments = eventComments;
    }

    public String getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    public event getEvent() {
        return event;
    }

    public void setEvent(event event) {
        this.event = event;
    }

    public List<visitorDemographics> getVisitorDemographics() {
        return visitorDemographics;
    }

    public void setVisitorDemographics(List<visitorDemographics> visitorDemographics) {
        this.visitorDemographics = visitorDemographics;
    }

    public Map<event, Integer> getEventAttendemce() {
        return eventAttendemce;
    }

    public void setEventAttendemce(Map<event, Integer> eventAttendemce) {
        this.eventAttendemce = eventAttendemce;
    }

    public Map<event, Float> getEventRatings() {
        return eventRatings;
    }

    public void setEventRatings(Map<event, Float> eventRatings) {
        this.eventRatings = eventRatings;
    }

    public List<String> getEventComments() {
        return eventComments;
    }

    public void setEventComments(List<String> eventComments) {
        this.eventComments = eventComments;
    }
}
