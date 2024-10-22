package com.example.springmvc.entity.Program;

import com.example.springmvc.entity.Client;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private int programId;

    @Column(name = "day")
    private int day;

    @Column(name = "week")
    private int week;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Program() {
    }

    public Program(int programId, int day, int week) {
        this.programId = programId;
        this.day = day;
        this.week = week;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
