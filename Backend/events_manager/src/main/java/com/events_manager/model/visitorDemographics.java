package com.events_manager.model;

public class visitorDemographics {
    private String ageGroup;
    private String gender;
    private int count;

    public visitorDemographics(String ageGroup, String gender, int count) {
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.count = count;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
