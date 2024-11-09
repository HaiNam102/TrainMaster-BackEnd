package com.example.springmvc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private int calendarId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "timestart")
    private LocalTime timestart;

    @Column(name = "timeend")
    private LocalTime timeend;

    @Column(name = "attendance_status")
    private Boolean attendanceStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    public Calendar() {
    }

    public Calendar(int calendarId, LocalDate date, LocalTime timestart, LocalTime timeend, Boolean attendanceStatus, Client client) {
        this.calendarId = calendarId;
        this.date = date;
        this.timestart = timestart;
        this.timeend = timeend;
        this.attendanceStatus = attendanceStatus;
        this.client = client;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimestart() {
        return timestart;
    }

    public void setTimestart(LocalTime timestart) {
        this.timestart = timestart;
    }

    public LocalTime getTimeend() {
        return timeend;
    }

    public void setTimeend(LocalTime timeend) {
        this.timeend = timeend;
    }

    public boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(Boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
