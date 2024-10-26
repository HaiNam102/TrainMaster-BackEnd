package com.example.springmvc.entity.Program;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercise_of_program")
public class ExerciseOfProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_of_program_id")
    private int exerciseOfProgramId;

    @Column(name = "sets_standard")
    private int setsStandard;

    @Column(name = "reps_standard")
    private int repsStandard;

    @Column(name = "set1")
    private Integer set1;

    @Column(name = "set2")
    private Integer set2;

    @Column(name = "set3")
    private Integer set3;

    @Column(name = "set4")
    private Integer set4;

    @Column(name = "set5")
    private Integer set5;

    @Column(name = "tempo", length = 50)
    private String tempo;

    @Column(name = "rir_rpe")
    private float rirRpe;

    @Column(name = "loadofexersise")
    private float loadOfExercise;

    @Column(name = "volume")
    private Integer volume;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonManagedReference
    private Exercise exercises;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonBackReference
    private Program program;

    public ExerciseOfProgram() {
    }

    public ExerciseOfProgram(int exerciseOfProgramId, int setsStandard, int repsStandard, Integer set1, Integer set2, Integer set3, Integer set4, Integer set5, String tempo, float rirRpe, float loadOfExercise, int volume) {
        this.exerciseOfProgramId = exerciseOfProgramId;
        this.setsStandard = setsStandard;
        this.repsStandard = repsStandard;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.set5 = set5;
        this.tempo = tempo;
        this.rirRpe = rirRpe;
        this.loadOfExercise = loadOfExercise;
        this.volume = volume;
    }

    public int getExerciseOfProgramId() {
        return exerciseOfProgramId;
    }

    public void setExerciseOfProgramId(int exerciseOfProgramId) {
        this.exerciseOfProgramId = exerciseOfProgramId;
    }

    public int getSetsStandard() {
        return setsStandard;
    }

    public void setSetsStandard(int setsStandard) {
        this.setsStandard = setsStandard;
    }

    public int getRepsStandard() {
        return repsStandard;
    }

    public void setRepsStandard(int repsStandard) {
        this.repsStandard = repsStandard;
    }

    public Integer getSet1() {
        return set1;
    }

    public void setSet1(Integer set1) {
        this.set1 = set1;
    }

    public Integer getSet2() {
        return set2;
    }

    public void setSet2(Integer set2) {
        this.set2 = set2;
    }

    public Integer getSet3() {
        return set3;
    }

    public void setSet3(Integer set3) {
        this.set3 = set3;
    }

    public Integer getSet4() {
        return set4;
    }

    public void setSet4(Integer set4) {
        this.set4 = set4;
    }

    public Integer getSet5() {
        return set5;
    }

    public void setSet5(Integer set5) {
        this.set5 = set5;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public float getRirRpe() {
        return rirRpe;
    }

    public void setRirRpe(float rirRpe) {
        this.rirRpe = rirRpe;
    }

    public float getLoadOfExercise() {
        return loadOfExercise;
    }

    public void setLoadOfExercise(float loadOfExercise) {
        this.loadOfExercise = loadOfExercise;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Exercise getExercises() {
        return exercises;
    }

    public void setExercises(Exercise exercises) {
        this.exercises = exercises;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}