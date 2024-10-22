package com.example.springmvc.dao.Meal_PlanRepository;

import com.example.springmvc.entity.MealPlan.FoodOfMeal;
import com.example.springmvc.entity.MealPlan.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOfMealRespository extends JpaRepository<FoodOfMeal, Integer>{
    List<FoodOfMeal> findByMealPlan(MealPlan mealPlan);
}
