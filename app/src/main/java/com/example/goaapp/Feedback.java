package com.example.goaapp;

public class Feedback {

    private String Title, Location,Experience, Suggestions,Ratings ;

    Feedback()
    {

    }

    Feedback(String title, String Location,String exp, String sug, String rate)
    {
        this.Title=title;
        this.Location=Location;
        this.Experience=exp;
        this.Suggestions=sug;
        this.Ratings=rate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getSuggestions() {
        return Suggestions;
    }

    public void setSuggestions(String suggestions) {
        Suggestions = suggestions;
    }

    public String getRatings() {
        return Ratings;
    }

    public void setRatings(String ratings) {
        Ratings = ratings;
    }
}

