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
    private int exerciseId;

    @Column(name = "exercisename", length = 255)
    private String exerciseName;

    // New field for muscle group
    @Column(name = "muscle_group", length = 255)
    private String muscleGroup;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<ExerciseOfProgram> exerciseOfPrograms;

    public Exercise() {
    }

    public Exercise(int exerciseId, String exerciseName, String muscleGroup) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.muscleGroup = muscleGroup;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
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
