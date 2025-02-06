package com.events_manager.model;

public class admin extends user {
    private String adminId;

    public admin(){
        super();
        this.role = "ADMIN";
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
