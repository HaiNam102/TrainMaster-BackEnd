package com.example.springmvc.DTO_Class;

import java.util.Date;

public class CreateClientTrackingDTO {
    private String clientName;
    private Date date;
    private float weight;
    private float sleepHour;
    private int stepCount;
    private String notes;

    public CreateClientTrackingDTO(String clientName, Date date, float weight, float sleepHour, int stepCount, String notes) {
        this.clientName = clientName;
        this.date = date;
        this.weight = weight;
        this.sleepHour = sleepHour;
        this.stepCount = stepCount;
        this.notes = notes;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getSleepHour() {
        return sleepHour;
    }

    public void setSleepHour(float sleepHour) {
        this.sleepHour = sleepHour;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
