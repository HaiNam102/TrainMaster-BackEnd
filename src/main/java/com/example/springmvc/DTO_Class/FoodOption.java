package com.example.springmvc.DTO_Class;

public class FoodOption {
    private int foodId;
    private String foodName;
    private String notes;
    private float kcal;
    private float protein;
    private float carb;
    private float fat;

    public FoodOption(int foodId, String foodName, String notes, float kcal, float protein, float carb, float fat) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.notes = notes;
        this.kcal = kcal;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
    }

    // Getters và Setters
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarb() {
        return carb;
    }

    public void setCarb(float carb) {
        this.carb = carb;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }
}
