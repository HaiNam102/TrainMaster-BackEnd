package com.example.springmvc.dao.Meal_PlanRepository;

import com.example.springmvc.DTO_Class.CreateMealPlanDTO;
import com.example.springmvc.DTO_Class.CreateProgramDTO;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.MealPlan.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPlanRespository extends JpaRepository<MealPlan,Integer> {
    List<MealPlan> findByClient(Client client);

    @Query("SELECT m.mealplan_id, m.day, m.session, " +
            "f.foodName, fom.protein, fom.fat, fom.carb, fom.note, fom.amount, f.unit " +
            "FROM MealPlan m " +
            "JOIN FeedbackNotification fn ON  m = fn.mealPlan " +
            "JOIN FoodOfMeal fom ON m = fom.mealPlan " +
            "JOIN Food f ON fom.food = f  " +
            "WHERE fn.approve = false")
    List<Object[]> findPendingMealPlans();
}
