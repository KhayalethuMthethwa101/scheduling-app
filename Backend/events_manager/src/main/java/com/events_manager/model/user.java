package com.events_manager.model;

public class user {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;

    public enum UserRole {
        ADMIN,
        VISITOR;
    };

    protected String role;

    public user(String usernameInput, String emailInput,String passwordInput,String phoneNumeberInput){
        this.email = emailInput;
        this.userName = usernameInput;
        this.password = passwordInput;
        this.phoneNumber = phoneNumeberInput;
    }

    public user(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public user(String password, String email) {
        this.password = password;
        this.email = email;
    }
    public user() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserRole(){
		return this.role;
	}
    
}
