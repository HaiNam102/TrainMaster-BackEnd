package com.example.springmvc.service.interface_service;

import com.example.springmvc.entity.MealPlan.Food;

import java.util.List;
import java.util.Optional;

public interface FoodService {
    public List<Food> getAllFoods();

    public Optional<Food> getFoodById(int id);

    public Food addFood(Food food);

    public Food getFoodByFoodName(String name);

    public Food updateFood(Food food);

    public Food deleteFoodById(int id);
}
