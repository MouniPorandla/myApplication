package com.example.mounika.myapplication.model;

/**
 * Created by Mounika on 7/1/2017.
 */


/**
 * Created by Mounika on 7/1/2017.
 */

public class Newslist {
    private String author;
    private String description;
    private String date;
    private String url;

    public Newslist(String author, String description, String date,  String url){
        this.description=description;
        this.author=author;
        this.url = url;
        this.date= date;
    }

    public String getTitle() {
        return author;
    }

    public void setTitle(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
