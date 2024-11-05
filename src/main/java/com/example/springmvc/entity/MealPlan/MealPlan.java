package com.example.springmvc.entity.MealPlan;

import com.example.springmvc.entity.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mealplan")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int mealplan_id;

    @Column(name = "training_status")
    private boolean training_status;

    @Column(name = "total_kcal", columnDefinition = "FLOAT DEFAULT 0") // New field
    private float total_kcal;

    @Column(name = "total_protein", columnDefinition = "FLOAT DEFAULT 0") // New field
    private float total_protein;

    @Column(name = "total_carb", columnDefinition = "FLOAT DEFAULT 0") // New field
    private float total_carb;

    @Column(name = "total_fat", columnDefinition = "FLOAT DEFAULT 0") // New field
    private float total_fat;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<FoodOfMeal> foodOfMeals;

    public MealPlan() {
    }

    public MealPlan(int mealplan_id, boolean training_status, Client client, float total_kcal, float total_protein, float total_carb, float total_fat) {
        this.mealplan_id = mealplan_id;
        this.training_status = training_status;
        this.client = client;
        this.total_kcal = total_kcal;
        this.total_protein = total_protein;
        this.total_carb = total_carb;
        this.total_fat = total_fat;
    }

    public int getMealplan_id() {
        return mealplan_id;
    }

    public void setMealplan_id(int mealplan_id) {
        this.mealplan_id = mealplan_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isTraining_status() { // Corrected method name
        return training_status;
    }

    public void setTraining_status(boolean training_status) {
        this.training_status = training_status;
    }

    public List<FoodOfMeal> getFoodOfMeals() {
        return foodOfMeals;
    }

    public void setFoodOfMeals(List<FoodOfMeal> foodOfMeals) {
        this.foodOfMeals = foodOfMeals;
    }

    // Getter and setter for total_kcal
    public float getTotal_kcal() {
        return total_kcal;
    }

    public void setTotal_kcal(float total_kcal) {
        this.total_kcal = total_kcal;
    }

    // Getter and setter for total_protein
    public float getTotal_protein() {
        return total_protein;
    }

    public void setTotal_protein(float total_protein) {
        this.total_protein = total_protein;
    }

    // Getter and setter for total_carb
    public float getTotal_carb() {
        return total_carb;
    }

    public void setTotal_carb(float total_carb) {
        this.total_carb = total_carb;
    }

    // Getter and setter for total_fat
    public float getTotal_fat() {
        return total_fat;
    }

    public void setTotal_fat(float total_fat) {
        this.total_fat = total_fat;
    }
}