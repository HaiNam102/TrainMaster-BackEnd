package com.example.springmvc.dao;

import com.example.springmvc.entity.MealPlan.Food;
import com.example.springmvc.entity.MealPlan.FoodOfMeal;
import com.example.springmvc.entity.MealPlan.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRespository extends JpaRepository<Food,Integer> {
    public Food findByFoodName(String name);
}
