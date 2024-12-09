package com.example.springmvc.entity.clienttracking;

import com.example.springmvc.entity.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clients_tracking")
public class ClientsTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracking_id")
    private int trackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "weight")
    private float weight;

    @Column(name = "sleep_hour")
    private float sleepHour;

    @Column(name = "step_count")
    private int stepCount;

    @Column(name = "notes", length = 255)
    private String notes;

    public ClientsTracking() {
    }

    public ClientsTracking(Client client, Date date, float weight, float sleepHour, int stepCount, String notes) {
        this.client = client;
        this.date = date;
        this.weight = weight;
        this.sleepHour = sleepHour;
        this.stepCount = stepCount;
        this.notes = notes;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
