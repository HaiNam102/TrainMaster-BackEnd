package com.example.springmvc.entity.MealPlan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "food_of_meal")
public class FoodOfMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int food_of_meal_id;

    @ManyToOne
    @JoinColumn(name = "mealplan_id")
    @JsonBackReference
    private MealPlan mealPlan;

    @ManyToOne
    @JoinColumn(name = "food_id")
    @JsonManagedReference
    private Food food;

    @Column(name = "protein")
    private float protein;

    @Column(name = "fat")
    private float fat;

    @Column(name = "carb")
    private float carb;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "amount")
    private int amount;

    public FoodOfMeal() {}

    public FoodOfMeal(int food_of_meal_id, MealPlan mealPlan, Food food, float protein, float fat, float carb, String note, int amount) {
        this.food_of_meal_id = food_of_meal_id;
        this.mealPlan = mealPlan;
        this.food = food;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
        this.note = note;
        this.amount = amount;
    }

    // Getters and Setters
    public int getFood_of_meal_id() {
        return food_of_meal_id;
    }

    public void setFood_of_meal_id(int food_of_meal_id) {
        this.food_of_meal_id = food_of_meal_id;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarb() {
        return carb;
    }

    public void setCarb(float carb) {
        this.carb = carb;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // New method to calculate kcals based on protein, carb, and fat
    public float getKcals() {
        return (protein * 4) + (carb * 4) + (fat * 9);
    }
}
