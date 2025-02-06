package com.events_manager.model;

public class feedback {
    private String feedbackId;
    private event event;
    private visitor visitor;
    public int rating;
    public int recommendation;
    public String comment;

    public feedback() {
    }

    public feedback(String feedbackId, event event, visitor visitor, int rating, int recommendation, String comment) {
        this.feedbackId = feedbackId;
        this.event = event;
        this.visitor = visitor;
        this.rating = rating;
        this.recommendation = recommendation;
        this.comment = comment;
    }

    public feedback(event event, int rating) {
        this.event = event;
        this.rating = rating;
    }


    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public event getEvent() {
        return event;
    }

    public void setEvent(event event) {
        this.event = event;
    }

    public visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(visitor visitor) {
        this.visitor = visitor;
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
