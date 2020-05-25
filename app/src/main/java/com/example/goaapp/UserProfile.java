package com.example.goaapp;

public class UserProfile {
    String fullname, email,contact;

    UserProfile()
    {

    }

    public UserProfile(String fullname, String email,String contact) {
        this.fullname = fullname;
        this.email = email;
        this.contact=contact;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
