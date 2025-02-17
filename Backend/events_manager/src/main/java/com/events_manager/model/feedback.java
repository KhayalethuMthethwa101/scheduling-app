package com.events_manager.model;

public class feedback {
    private String feedbackId;
    private String eventId;
    private String email;
    public int rating;
    public int recommendation;
    public String comment;

    public feedback() {
    }

    public feedback(String feedbackId, String eventId, String email, int rating, int recommendation, String comment) {
        this.feedbackId = feedbackId;
        this.eventId = eventId;
        this.email = email;
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
        return eventId;
    }

    public void setEventID(String eventId) {
        this.eventId = eventId;
    }

    public String getVisitorID() {
        return email;
    }

    public void setVisitorID(String email) {
        this.email = email;
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
