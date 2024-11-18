package com.example.springmvc.entity;

import com.example.springmvc.entity.MealPlan.MealPlan;
import com.example.springmvc.entity.Program.Program;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "feedback_notification")
public class FeedbackNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;

    private String feedback;

    private boolean approve;


    // Quan hệ một - một với MealPlan
    @OneToOne
    @JoinColumn(name = "mealplan_id")
    private MealPlan mealPlan;

    // Quan hệ một - một với Program
    @OneToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "fm_id")
    @JsonIgnore
    private FitnessManager fitnessManager;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "created_at")
    private Date created_at;

    public FeedbackNotification() {
    }

    public FeedbackNotification(int id, String message, String feedback, boolean approve, boolean isRead,Date created_at) {
        this.id = id;
        this.message = message;
        this.feedback = feedback;
        this.approve = approve;
        this.isRead = isRead;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public FitnessManager getFitnessManager() {
        return fitnessManager;
    }

    public void setFitnessManager(FitnessManager fitnessManager) {
        this.fitnessManager = fitnessManager;
    }
}

