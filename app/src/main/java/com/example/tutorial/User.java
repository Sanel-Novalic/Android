package com.example.tutorial;

import java.io.Serializable;

public class User implements Serializable {
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    private String userLogin;
    private String userName;
    private int imageResourceId;

    public User(String userLogin, String userName, int imageResourceId){
        this.userLogin = userLogin;
        this.userName = userName;
        this.imageResourceId = imageResourceId;
    }
}
