package com.example.springmvc.DTO_Class;

public class UpdateFeedbackDTO {
    private String feedback;
    private boolean approve;
    private String fitnessManagerName; // Include the fitness manager's name

    // Getters and setters
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getFitnessManagerName() {
        return fitnessManagerName;
    }

    public void setFitnessManagerName(String fitnessManagerName) {
        this.fitnessManagerName = fitnessManagerName;
    }
}
