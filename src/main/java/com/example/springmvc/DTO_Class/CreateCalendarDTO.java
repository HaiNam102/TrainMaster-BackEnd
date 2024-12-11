package com.example.springmvc.DTO_Class;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateCalendarDTO {
    private int calendarId;
    private String clientName;
    private LocalDate date;
    private LocalTime timestart;
    private LocalTime timeend;
    private Boolean attendanceStatus;

    public CreateCalendarDTO(int calendarId, boolean attendanceStatus, LocalDate date, LocalTime timestart, LocalTime timeend) {
        this.calendarId = calendarId;
        this.attendanceStatus = attendanceStatus;
        this.date = date;
        this.timestart = timestart;
        this.timeend = timeend;

    }
    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

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

    public Boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(Boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public LocalTime getTimeend() {
        return timeend;
    }

    public void setTimeend(LocalTime timeend) {
        this.timeend = timeend;
    }
}