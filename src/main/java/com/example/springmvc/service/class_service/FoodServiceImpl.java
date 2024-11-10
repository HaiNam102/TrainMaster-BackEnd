package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.Meal_PlanRepository.FoodRespository;
import com.example.springmvc.entity.MealPlan.Food;
import com.example.springmvc.service.interface_service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {
    private FoodRespository foodRespository;

    @Autowired
    public FoodServiceImpl(FoodRespository foodRespository) {
        this.foodRespository = foodRespository;
    }

    @Override
    public List<Food> getAllFoods() {
        return this.foodRespository.findAll();
    }

    @Override
    public Optional<Food> getFoodById(int id) {
        return this.foodRespository.findById(id);
    }

    @Override
    @Transactional
    public Food addFood(Food food) {
        return this.foodRespository.save(food);
    }

    @Override
    @Transactional
    public Food getFoodByFoodName(String name) {
        return this.foodRespository.findByFoodName(name);
    }

    @Override
    @Transactional
    public Food updateFood(Food food) {
        return this.foodRespository.saveAndFlush(food);
    }

    @Override
    @Transactional
    public Food deleteFoodById(int id) {
        this.foodRespository.findById(id);
        return null;
    }
}
