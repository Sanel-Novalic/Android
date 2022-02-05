package com.example.tutorial;

import java.io.Serializable;

public class Message implements Serializable {
    private String title;
    private String content;

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setTitle(String Title) { this.title = Title; }
    public void setContent(String Content) { this.content = Content; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
}
