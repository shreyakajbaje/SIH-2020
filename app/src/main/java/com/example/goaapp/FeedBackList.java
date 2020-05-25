package com.example.goaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FeedBackList  {

    private String Title;
    private String Location;
    // private String Image;

    public FeedBackList()
    {


    }

    public FeedBackList( String location, String title)
    {
        // this.Image=image;
        this.Title=title;
        this.Location=location;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }


}

