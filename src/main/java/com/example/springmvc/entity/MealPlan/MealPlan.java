package com.example.springmvc.entity.MealPlan;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Feedback;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mealplan")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealplan_id;

    @Column(name = "training_status")
    private boolean training_status;

    // New fields for day and session
    @Column(name = "day")
    private LocalDate day;

    @Column(name = "session", length = 255)
    private String session;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<FoodOfMeal> foodOfMeals;

    @OneToOne
    @JoinColumn(name = "feedback_id")
    @JsonIgnore
    private Feedback feedback;

    public MealPlan() {}

    public MealPlan(int mealplan_id, boolean training_status, Client client, LocalDate day, String session) {
        this.mealplan_id = mealplan_id;
        this.training_status = training_status;
        this.client = client;
        this.day = day;
        this.session = session;
    }

    // Existing getters and setters
    public int getMealplan_id() { return mealplan_id; }
    public void setMealplan_id(int mealplan_id) { this.mealplan_id = mealplan_id; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public boolean getTrainingstatus() { return training_status; }
    public void setTrainingstatus(boolean training_status) { this.training_status = training_status; }
    public List<FoodOfMeal> getFoodOfMeals() { return foodOfMeals; }
    public void setFoodOfMeals(List<FoodOfMeal> foodOfMeals) { this.foodOfMeals = foodOfMeals; }

    // New getters and setters for day and session
    public LocalDate getDay() { return day; }
    public void setDay(LocalDate day) { this.day = day; }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    // Calculations for total kcal, protein, carb, and fat
    public float getTotalKcal() {
        return foodOfMeals.stream()
                .map(FoodOfMeal::getKcals)
                .reduce(0f, Float::sum);
    }

    public float getTotalProtein() {
        return foodOfMeals.stream()
                .map(FoodOfMeal::getProtein)
                .reduce(0f, Float::sum);
    }

    public float getTotalCarb() {
        return foodOfMeals.stream()
                .map(FoodOfMeal::getCarb)
                .reduce(0f, Float::sum);
    }

    public float getTotalFat() {
        return foodOfMeals.stream()
                .map(FoodOfMeal::getFat)
                .reduce(0f, Float::sum);
    }
}
