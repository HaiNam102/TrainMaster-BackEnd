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

    @ManyToOne
    @JoinColumn(name = "clients_id")
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "mealPlan",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<FoodOfMeal> foodOfMeals;

    public MealPlan() {
    }

    public MealPlan(int mealplan_id, boolean training_status, Client client) {
        this.mealplan_id = mealplan_id;
        this.training_status = training_status;
        this.client = client;
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

    public boolean isTraining_status() {
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
}
