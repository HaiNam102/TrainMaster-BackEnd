package com.example.springmvc.DTO_Class;

import java.util.List;


public class CreateProgramDTO {
    private String clientName;
    private int day;
    private int week;

    // A list of exercises
    private List<CreateExerciseDTO> exercises;

    public CreateProgramDTO() {
    }

    public CreateProgramDTO(String clientName, int day, int week, List<CreateExerciseDTO> exercises) {
        this.clientName = clientName;
        this.day = day;
        this.week = week;
        this.exercises = exercises;
    }

    // Getters and Setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public List<CreateExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<CreateExerciseDTO> exercises) {
        this.exercises = exercises;
    }
}

