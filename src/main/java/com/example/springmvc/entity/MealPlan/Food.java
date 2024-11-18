package com.example.springmvc.entity.MealPlan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int  foodId;

    @Column(name = "food_name", length = 255)
    private String foodName;

    @Column(name = "unit", length = 10)
    private String unit;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FoodOfMeal> foodOfMeals;

    public Food() {}

    public Food(int foodId, String foodName, String unit) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.unit = unit;
    }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public List<FoodOfMeal> getFoodOfMeals() { return foodOfMeals; }
    public void setFoodOfMeals(List<FoodOfMeal> foodOfMeals) { this.foodOfMeals = foodOfMeals; }
}
