package com.example.springmvc.entity.Program;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.FeedbackNotification;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programId;

    @Column(name = "day")
    private int day;

    @Column(name = "week")
    private int week;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ExerciseOfProgram> exerciseOfPrograms;

    @OneToOne(mappedBy = "program")
    private FeedbackNotification feedbackNotification;

    public Program() {

    }
    public Program(int programId, int day, int week, Client client) {
        this.programId = programId;
        this.day = day;
        this.week = week;
        this.client = client;
    }



    public int getProgramId() { return programId; }
    public void setProgramId(int programId) { this.programId = programId; }
    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }
    public int getWeek() { return week; }
    public void setWeek(int week) { this.week = week; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public List<ExerciseOfProgram> getExerciseOfPrograms() { return exerciseOfPrograms; }
    public void setExerciseOfPrograms(List<ExerciseOfProgram> exerciseOfPrograms) { this.exerciseOfPrograms = exerciseOfPrograms; }

    // Method to calculate total volume of exercises in this program
    public int getTotalVolume() {
        return exerciseOfPrograms.stream()
                .mapToInt(ExerciseOfProgram::getVolume)
                .sum();
    }
}
