package com.adarsha.birthday_wisher.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "birthdays")
public class Birthday {

    @Id
    private String id;

    private String name;
    private String email;
    private int month;
    private int day;

    // constructor
    public Birthday() {}

    public Birthday(String name, String email, int month, int day) {
        this.name = name;
        this.email = email;
        this.month = month;
        this.day = day;
    }

    //Getters ans Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
}
