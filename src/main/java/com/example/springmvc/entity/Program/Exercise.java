package com.example.springmvc.entity.Program;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private int exercise_id;

    @Column(name = "exercisename", length = 255)
    private String exerciseName;

    @OneToMany(mappedBy = "exercises", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ExerciseOfProgram> exerciseOfPrograms;

    public Exercise() {
    }

    public Exercise(int exercise_id, String exerciseName) {
        this.exercise_id = exercise_id;
        this.exerciseName = exerciseName;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercisename() {
        return exerciseName;
    }

    public void setExercisename(String exercisename) {
        this.exerciseName = exercisename;
    }
}
