package com.example.springmvc.dao.Meal_PlanRepository;

import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.MealPlan.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPlanRespository extends JpaRepository<MealPlan,Integer> {
    List<MealPlan> findByClient(Client client);

    @Query("SELECT m.mealplan_id, m.day, m.session, m.training_status, " +
            "f.foodName, fom.protein, fom.fat, fom.carb, fom.note, fom.amount, f.unit " +
            "FROM MealPlan m " +
            "JOIN FeedbackNotification fn ON  m = fn.mealPlan " +
            "JOIN FoodOfMeal fom ON m = fom.mealPlan " +
            "JOIN Food f ON fom.food = f  " +
            "WHERE fn.approve = false")
    List<Object[]> findPendingMealPlans();


    @Query("SELECT m.mealplan_id, m.day, m.session, m.training_status," +
            "f.foodName, fom.protein, fom.fat, fom.carb, fom.note, fom.amount, f.unit " +
            "FROM MealPlan m " +
            "JOIN m.client c " +
            "JOIN c.account a " +
            "JOIN FeedbackNotification fn ON m = fn.mealPlan " +
            "JOIN FoodOfMeal fom ON m = fom.mealPlan " +
            "JOIN Food f ON fom.food = f " +
            "WHERE fn.approve = true AND a.username = :username " +
            "GROUP BY m.mealplan_id, m.day, m.session, m.training_status, f.foodName, fom.protein, fom.fat, fom.carb, fom.note, fom.amount, f.unit")
    List<Object[]> findApprovedMealPlansByUsername(@Param("username") String username);

}
