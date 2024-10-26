package com.example.springmvc.DTO_Class;

import java.util.List;

public class CreateMealPlanDTO {
    private String clientName;
    private boolean trainingStatus;
    private List<String> selectedFoodNames;

    // Getters và Setters
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

    public List<String> getSelectedFoodNames() {
        return selectedFoodNames;
    }

    public void setSelectedFoodNames(List<String> selectedFoodNames) {
        this.selectedFoodNames = selectedFoodNames;
    }
}