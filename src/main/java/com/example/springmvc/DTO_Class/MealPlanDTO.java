package com.example.springmvc.DTO_Class;

import java.util.List;

public class MealPlanDTO {
    private int mealplanId;
    private int trainingStatus;
    private int clientsId;
    private List<FoodOption> foods; // Danh sách các món ăn liên quan

    // Getters và Setters
    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public int getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(int trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public int getClientsId() {
        return clientsId;
    }

    public void setClientsId(int clientsId) {
        this.clientsId = clientsId;
    }

    public List<FoodOption> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodOption> foods) {
        this.foods = foods;
    }
}
