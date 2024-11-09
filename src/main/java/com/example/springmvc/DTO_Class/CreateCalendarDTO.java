package com.example.springmvc.DTO_Class;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateCalendarDTO {
    private String clientName;
    private LocalDate date;
    private LocalTime timestart;
    private LocalTime timeend;

    // Getters và Setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalTime getTimestart() {
        return timestart;
    }

    public void setTimestart(LocalTime timestart) {
        this.timestart = timestart;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeend() {
        return timeend;
    }

    public void setTimeend(LocalTime timeend) {
        this.timeend = timeend;
    }
}