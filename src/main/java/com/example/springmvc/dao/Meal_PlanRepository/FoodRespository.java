package com.example.springmvc.dao.Meal_PlanRepository;

import com.example.springmvc.entity.MealPlan.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRespository extends JpaRepository<Food,Integer> {
    public Food findByFoodName(String name);
}
