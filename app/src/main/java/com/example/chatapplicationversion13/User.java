package com.example.chatapplicationversion13;

public class User {
    private String userId;
    private String userName;
    private String email;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public String getUserEmail() {
        return email;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}