package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.CreateMealPlanDTO;
import com.example.springmvc.DTO_Class.FoodOfMealDTO;
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

import java.util.ArrayList;
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
        // Find Client by name
        Client client = clientRespository.findByFirstName(clientName);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Client not found!"));
        }

        // Find all MealPlans related to the Client
        List<MealPlan> mealPlans = mealPlanRepository.findByClient(client);

        // Create a response map
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
            // Client not found, return a response with a message
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Create a new MealPlan with day and session information
        MealPlan mealPlan = new MealPlan();
        mealPlan.setClient(client);
        mealPlan.setTrainingstatus(mealPlanDTO.getTrainingStatus());
        mealPlan.setDay(mealPlanDTO.getDay());
        mealPlan.setSession(mealPlanDTO.getSession());

        // Initialize a list to hold FoodOfMeal entities
        List<FoodOfMeal> foodOfMeals = new ArrayList<>();

        // Loop through selected food items in the DTO
        for (FoodOfMealDTO foodDTO : mealPlanDTO.getSelectedFoodItems()) {
            // Find the food by name
            Food food = foodRepository.findByFoodName(foodDTO.getFoodName());

            if (food != null) {
                // Create a new FoodOfMeal entity and set its properties
                FoodOfMeal foodOfMeal = new FoodOfMeal();
                foodOfMeal.setMealPlan(mealPlan); // Associate the food item with the meal plan
                foodOfMeal.setFood(food);
                foodOfMeal.setProtein(foodDTO.getProtein());
                foodOfMeal.setFat(foodDTO.getFat());
                foodOfMeal.setCarb(foodDTO.getCarb());
                foodOfMeal.setAmount(foodDTO.getAmount());
                foodOfMeal.setNote(foodDTO.getNote());

                // Add to the list
                foodOfMeals.add(foodOfMeal);
            } else {
                // If food not found, return a response with a message
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Food not found: " + foodDTO.getFoodName());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }

        // Set the list of FoodOfMeal entities to the meal plan
        mealPlan.setFoodOfMeals(foodOfMeals);

        // Save the meal plan to the database
        mealPlan = mealPlanRepository.save(mealPlan);

        // Return a success message with the created meal plan
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", "Meal plan created successfully");
        successResponse.put("mealPlan", mealPlan);

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }


}

