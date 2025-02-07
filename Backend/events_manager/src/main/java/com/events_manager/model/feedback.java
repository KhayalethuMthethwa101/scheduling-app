package com.events_manager.model;

public class feedback {
    private String feedbackId;
    private String eventID;
    private String visitorID;
    public int rating;
    public int recommendation;
    public String comment;

    public feedback() {
    }

    public feedback(String feedbackId, String eventID, String visitorID, int rating, int recommendation, String comment) {
        this.feedbackId = feedbackId;
        this.eventID = eventID;
        this.visitorID = visitorID;
        this.rating = rating;
        this.recommendation = recommendation;
        this.comment = comment;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(String visitorID) {
        this.visitorID = visitorID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(int recommendation) {
        this.recommendation = recommendation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
