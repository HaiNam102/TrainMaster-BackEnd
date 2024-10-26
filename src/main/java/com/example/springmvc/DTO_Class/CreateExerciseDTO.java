package com.example.springmvc.DTO_Class;

public class CreateExerciseDTO {
    private String selectedExerciseName;
    private int setsStandard;
    private int repsStandard;
    private int set1;
    private int set2;
    private int set3;
    private int set4;
    private int set5;
    private String tempo;
    private float rirRpe;

    public CreateExerciseDTO(String selectedExerciseName, int setsStandard, int repsStandard, int set1, int set2, int set3, int set4, int set5, String tempo, float rirRpe) {
        this.selectedExerciseName = selectedExerciseName;
        this.setsStandard = setsStandard;
        this.repsStandard = repsStandard;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.set5 = set5;
        this.tempo = tempo;
        this.rirRpe = rirRpe;
    }

    // Getters and Setters
    public String getSelectedExerciseName() {
        return selectedExerciseName;
    }

    public void setSelectedExerciseName(String selectedExerciseName) {
        this.selectedExerciseName = selectedExerciseName;
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

    public int getSet1() {
        return set1;
    }

    public void setSet1(int set1) {
        this.set1 = set1;
    }

    public int getSet2() {
        return set2;
    }

    public void setSet2(int set2) {
        this.set2 = set2;
    }

    public int getSet3() {
        return set3;
    }

    public void setSet3(int set3) {
        this.set3 = set3;
    }

    public int getSet4() {
        return set4;
    }

    public void setSet4(int set4) {
        this.set4 = set4;
    }

    public int getSet5() {
        return set5;
    }

    public void setSet5(int set5) {
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
}
