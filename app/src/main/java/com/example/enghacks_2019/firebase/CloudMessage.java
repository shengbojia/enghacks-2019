package com.example.enghacks_2019.firebase;

import com.google.firebase.Timestamp;

import java.sql.Time;
import java.util.Calendar;

public class CloudMessage {
    private String description;
    private Timestamp timeStamp;

    public CloudMessage() {
        // Need empty constructor
    }

    public CloudMessage(
            String description,
            Timestamp timeStamp
    ) {
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        // Don't change getter method name
        return description;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }
}
