package com.example.springmvc.DTO_Class;

import java.time.LocalDate;
import java.util.List;

public class CreateMealPlanDTO {

    private String clientName;
    private boolean trainingStatus;
    private LocalDate day;
    private String session;
    private List<FoodOfMealDTO> selectedFoodItems;

    // Getters and Setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public boolean getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(boolean trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<FoodOfMealDTO> getSelectedFoodItems() {
        return selectedFoodItems;
    }

    public void setSelectedFoodItems(List<FoodOfMealDTO> selectedFoodItems) {
        this.selectedFoodItems = selectedFoodItems;
    }
}

