package com.example.springmvc.entity.MealPlan;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int foodId;

    @Column(name = "food_name", length = 255)
    private String foodName;

    @Column(name = "notes", length = 255)
    private String notes;

    @Column(name = "kcal")
    private float kcal;

    @Column(name = "protein")
    private float protein;

    @Column(name = "carb")
    private float carb;

    @Column(name = "fat")
    private float fat;

    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<FoodOfMeal> foodOfMeals;

    public Food() {
    }

    public Food(int foodId, String foodName, String notes, float kcal, float protein, float carb, float fat) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.notes = notes;
        this.kcal = kcal;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarb() {
        return carb;
    }

    public void setCarb(float carb) {
        this.carb = carb;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }
}