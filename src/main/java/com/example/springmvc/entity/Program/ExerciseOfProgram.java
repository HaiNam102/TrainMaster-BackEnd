package com.example.springmvc.entity.Program;

import com.example.springmvc.entity.Program.Exercise;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonManagedReference
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonBackReference
    private Program program;

    public ExerciseOfProgram() {
    }

    public Integer getSet5() {
        return set5;
    }

    public void setSet5(Integer set5) {
        this.set5 = set5;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercises(Exercise exercise) {
        this.exercise = exercise;
    }

    public float getLoadOfExercise() {
        return loadOfExercise;
    }

    public void setLoadOfExercise(float loadOfExercise) {
        this.loadOfExercise = loadOfExercise;
    }

    public float getRirRpe() {
        return rirRpe;
    }

    public void setRirRpe(float rirRpe) {
        this.rirRpe = rirRpe;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Integer getSet4() {
        return set4;
    }

    public void setSet4(Integer set4) {
        this.set4 = set4;
    }

    public Integer getSet3() {
        return set3;
    }

    public void setSet3(Integer set3) {
        this.set3 = set3;
    }

    public Integer getSet2() {
        return set2;
    }

    public void setSet2(Integer set2) {
        this.set2 = set2;
    }

    public Integer getSet1() {
        return set1;
    }

    public void setSet1(Integer set1) {
        this.set1 = set1;
    }

    public int getRepsStandard() {
        return repsStandard;
    }

    public void setRepsStandard(int repsStandard) {
        this.repsStandard = repsStandard;
    }

    public int getSetsStandard() {
        return setsStandard;
    }

    public void setSetsStandard(int setsStandard) {
        this.setsStandard = setsStandard;
    }

    public int getExerciseOfProgramId() {
        return exerciseOfProgramId;
    }

    public void setExerciseOfProgramId(int exerciseOfProgramId) {
        this.exerciseOfProgramId = exerciseOfProgramId;
    }


    public int getVolume() {
        int totalVolume = 0;
        totalVolume += (set1 != null ? set1 : 0);
        totalVolume += (set2 != null ? set2 : 0);
        totalVolume += (set3 != null ? set3 : 0);
        totalVolume += (set4 != null ? set4 : 0);
        totalVolume += (set5 != null ? set5 : 0);
        return totalVolume;
    }
}
