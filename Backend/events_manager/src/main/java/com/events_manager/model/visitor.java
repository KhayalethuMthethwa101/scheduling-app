package com.events_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class visitor extends user {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String visitorId;

    public visitor(String usernameInput, String emailInput, String passwordInput, String phoneNumeberInput, String visitorId) {
        super(usernameInput, emailInput, passwordInput, phoneNumeberInput);
        this.visitorId = visitorId;
    }

    public visitor(String userName, String email, String password, String visitorId) {
        super(userName, email, password);
        this.visitorId = visitorId;
    }

    public visitor(String password, String email, String visitorId) {
        super(password, email);
        this.visitorId = visitorId;
    }

    public visitor(String visitorId) {
        this.visitorId = visitorId;
    }

    public visitor() {
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }
}
