package com.example.springmvc.entity.Program;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    // New field for muscle group
    @Column(name = "muscle_group", length = 255)
    private String muscleGroup;

    @OneToMany(mappedBy = "exercises", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<ExerciseOfProgram> exerciseOfPrograms;

    public Exercise() {
    }

    public Exercise(int exercise_id, String exerciseName, String muscleGroup) {
        this.exercise_id = exercise_id;
        this.exerciseName = exerciseName;
        this.muscleGroup = muscleGroup;
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

    // Getter and setter for muscleGroup
    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
