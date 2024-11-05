package com.example.springmvc.dao.Meal_PlanRepository;

import com.example.springmvc.entity.MealPlan.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRespository extends JpaRepository<Food,Integer> {
    public Food findByFoodName(String name);

    public Food deleteByFoodName(String name);

    @Query("SELECT f.foodName FROM Food f")
    public List<String> findAllFoodNames();

}
