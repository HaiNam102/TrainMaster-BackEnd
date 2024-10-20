package com.example.springmvc.entity.MealPlan;

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
    private MealPlan mealPlan;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    public FoodOfMeal() {
    }

    public FoodOfMeal(int food_of_meal_id, MealPlan mealPlan, Food food) {
        this.food_of_meal_id = food_of_meal_id;
        this.mealPlan = mealPlan;
        this.food = food;
    }

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
}
