package com.example.springmvc.entity;

import com.example.springmvc.entity.MealPlan.MealPlan;
import com.example.springmvc.entity.Program.Program;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedbackId;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "approve")
    private boolean approve;

    @ManyToOne
    @JoinColumn(name = "fm_id")
    @JsonBackReference
    private FitnessManager fitnessManager;

    @OneToOne
    @JoinColumn(name = "mealplan_id")
    @JsonIgnore
    private MealPlan mealplan;

    @OneToOne
    @JoinColumn(name = "program_id")
    @JsonIgnore
    private Program program;

    // Constructors, getters, and setters
    public Feedback() {}

    public Feedback(String feedback, boolean approve, FitnessManager fitnessManager, MealPlan mealplan, Program program) {
        this.feedback = feedback;
        this.approve = approve;
        this.fitnessManager = fitnessManager;
        this.mealplan = mealplan;
        this.program = program;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

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

    public MealPlan getMealplan() {
        return mealplan;
    }

    public void setMealplan(MealPlan mealplan) {
        this.mealplan = mealplan;
    }

    public FitnessManager getFitnessManager() {
        return fitnessManager;
    }

    public void setFitnessManager(FitnessManager fitnessManager) {
        this.fitnessManager = fitnessManager;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }


}
