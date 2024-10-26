package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.CreateMealPlanDTO;
import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.dao.Meal_PlanRepository.FoodOfMealRespository;
import com.example.springmvc.dao.Meal_PlanRepository.FoodRespository;
import com.example.springmvc.dao.Meal_PlanRepository.MealPlanRespository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.MealPlan.Food;
import com.example.springmvc.entity.MealPlan.FoodOfMeal;
import com.example.springmvc.entity.MealPlan.MealPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mealPlans")
@CrossOrigin(origins = "http://localhost:63342")  // Allow requests from your frontend
public class MealPlanController {

    @Autowired
    private MealPlanRespository mealPlanRepository;
    @Autowired
    private FoodRespository foodRepository;
    @Autowired
    private FoodOfMealRespository foodOfMealRepository;
    @Autowired
    private ClientRespository clientRespository;

    @GetMapping("/client/{clientName}")
    public ResponseEntity<Map<String, Object>> getMealPlansByClientName(@PathVariable String clientName) {
        // Tìm Client theo tên
        Client client = clientRespository.findByFirstName(clientName);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Client not found!"));
        }

        // Tìm tất cả MealPlans liên quan đến Client này
        List<MealPlan> mealPlans = mealPlanRepository.findByClient(client);

        // Tạo một Map để lưu kết quả phản hồi
        Map<String, Object> response = new HashMap<>();
        response.put("clientName", client.getFirstName());
        response.put("mealPlans", mealPlans);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createMealPlan(@RequestBody CreateMealPlanDTO mealPlanDTO) {
        // Find Client by name
        Client client = clientRespository.findByFirstName(mealPlanDTO.getClientName());
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Client not found!"));
        }

        // Create a new MealPlan
        MealPlan newMealPlan = new MealPlan();
        newMealPlan.setClient(client);
        newMealPlan.setTraining_status(mealPlanDTO.getTrainingStatus());

        // Save the meal plan
        MealPlan mealPlan = mealPlanRepository.save(newMealPlan);

        // Calculate totals while saving food to the meal plan
        double totalKcal = 0, totalProtein = 0, totalCarbs = 0, totalFat = 0;

        for (String foodName : mealPlanDTO.getSelectedFoodNames()) {
            Food food = foodRepository.findByFoodName(foodName);
            if (food != null) {
                // Add food to the meal plan
                FoodOfMeal foodOfMeal = new FoodOfMeal();
                foodOfMeal.setMealPlan(mealPlan);
                foodOfMeal.setFood(food);
                foodOfMealRepository.save(foodOfMeal);

                // Update totals
                totalKcal += food.getKcal();
                totalProtein += food.getProtein();
                totalCarbs += food.getCarb();
                totalFat += food.getFat();
            }
        }

        // Return response with meal plan and totals
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Meal Plan created successfully!");
        response.put("mealplanId", mealPlan.getMealplan_id());
        response.put("totalKcal", totalKcal);
        response.put("totalProtein", totalProtein);
        response.put("totalCarbs", totalCarbs);
        response.put("totalFat", totalFat);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
