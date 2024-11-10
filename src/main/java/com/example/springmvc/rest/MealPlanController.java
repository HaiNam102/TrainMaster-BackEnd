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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/mealPlans")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from your frontend
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

    @GetMapping("/get/{mealPlanId}")
    public ResponseEntity<Map<String, Object>> getMealPlanById(@PathVariable int mealPlanId) {
        // Find the meal plan by ID
        Optional<MealPlan> mealPlanOpt = mealPlanRepository.findById(mealPlanId);
        if (mealPlanOpt.isEmpty()) {
            // Meal plan not found, return a response with a message
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Meal plan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        MealPlan mealPlan = mealPlanOpt.get();

        // Structure the response data
        Map<String, Object> mealPlanResponse = new HashMap<>();
        mealPlanResponse.put("clientName", mealPlan.getClient().getFirstName());
        mealPlanResponse.put("trainingStatus", mealPlan.getTrainingstatus());
        mealPlanResponse.put("day", mealPlan.getDay());
        mealPlanResponse.put("session", mealPlan.getSession());

        // Initialize a list to hold food items in the meal plan
        List<Map<String, Object>> foodItems = new ArrayList<>();

        // Loop through each FoodOfMeal item in the meal plan
        for (FoodOfMeal foodOfMeal : mealPlan.getFoodOfMeals()) {
            Map<String, Object> foodItem = new HashMap<>();
            foodItem.put("foodName", foodOfMeal.getFood().getFoodName());
            foodItem.put("protein", foodOfMeal.getProtein());
            foodItem.put("fat", foodOfMeal.getFat());
            foodItem.put("carb", foodOfMeal.getCarb());
            foodItem.put("amount", foodOfMeal.getAmount());
            foodItem.put("note", foodOfMeal.getNote());

            // Add the food item to the list
            foodItems.add(foodItem);
        }

        // Add the food items to the meal plan response
        mealPlanResponse.put("foodItems", foodItems);

        // Return the meal plan response
        return ResponseEntity.ok(mealPlanResponse);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Map<String, Object>>> getAllMealPlans() {
        // Retrieve all meal plans from the database
        List<MealPlan> mealPlans = mealPlanRepository.findAll();

        // Initialize a list to hold the response for each meal plan
        List<Map<String, Object>> mealPlanResponses = new ArrayList<>();

        // Loop through each meal plan and structure the response
        for (MealPlan mealPlan : mealPlans) {
            Map<String, Object> mealPlanResponse = new HashMap<>();
            mealPlanResponse.put("mealplan_id", mealPlan.getMealplan_id());
            mealPlanResponse.put("clientName", mealPlan.getClient().getFirstName());
            mealPlanResponse.put("trainingStatus", mealPlan.getTrainingstatus());
            mealPlanResponse.put("day", mealPlan.getDay());
            mealPlanResponse.put("session", mealPlan.getSession());

            // Initialize a list to hold food items in the meal plan
            List<Map<String, Object>> foodItems = new ArrayList<>();

            // Loop through each FoodOfMeal item in the meal plan
            for (FoodOfMeal foodOfMeal : mealPlan.getFoodOfMeals()) {
                Map<String, Object> foodItem = new HashMap<>();
                foodItem.put("foodName", foodOfMeal.getFood().getFoodName());
                foodItem.put("protein", foodOfMeal.getProtein());
                foodItem.put("fat", foodOfMeal.getFat());
                foodItem.put("carb", foodOfMeal.getCarb());
                foodItem.put("amount", foodOfMeal.getAmount());
                foodItem.put("note", foodOfMeal.getNote());

                // Add the food item to the list
                foodItems.add(foodItem);
            }

            // Add the food items to the meal plan response
            mealPlanResponse.put("foodItems", foodItems);

            // Add the meal plan response to the list of meal plans
            mealPlanResponses.add(mealPlanResponse);
        }

        // Return the list of meal plans
        return ResponseEntity.ok(mealPlanResponses);
    }

    @PostMapping("/create")
    @Transactional
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

    @PutMapping("/update/{mealPlanId}")
    @Transactional
    public ResponseEntity<Map<String, Object>> updateMealPlan(
            @PathVariable int mealPlanId,
            @RequestBody CreateMealPlanDTO mealPlanDTO) {

        // Find the existing meal plan by ID
        Optional<MealPlan> existingMealPlanOpt = mealPlanRepository.findById(mealPlanId);
        if (existingMealPlanOpt.isEmpty()) {
            // Meal plan not found, return a response with a message
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Meal plan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        MealPlan existingMealPlan = existingMealPlanOpt.get();

        // Find Client by name
        Client client = clientRespository.findByFirstName(mealPlanDTO.getClientName());
        if (client == null) {
            // Client not found, return a response with a message
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Update meal plan properties
        existingMealPlan.setClient(client);
        existingMealPlan.setTrainingstatus(mealPlanDTO.getTrainingStatus());
        existingMealPlan.setDay(mealPlanDTO.getDay());
        existingMealPlan.setSession(mealPlanDTO.getSession());

        // Clear existing FoodOfMeal entries for this meal plan
        foodOfMealRepository.deleteAll(existingMealPlan.getFoodOfMeals());

        // Initialize a list to hold updated FoodOfMeal entities
        List<FoodOfMeal> updatedFoodOfMeals = new ArrayList<>();

        // Loop through selected food items in the DTO
        for (FoodOfMealDTO foodDTO : mealPlanDTO.getSelectedFoodItems()) {
            // Find the food by name
            Food food = foodRepository.findByFoodName(foodDTO.getFoodName());

            if (food != null) {
                // Create a new FoodOfMeal entity and set its properties
                FoodOfMeal foodOfMeal = new FoodOfMeal();
                foodOfMeal.setMealPlan(existingMealPlan); // Associate the food item with the meal plan
                foodOfMeal.setFood(food);
                foodOfMeal.setProtein(foodDTO.getProtein());
                foodOfMeal.setFat(foodDTO.getFat());
                foodOfMeal.setCarb(foodDTO.getCarb());
                foodOfMeal.setAmount(foodDTO.getAmount());
                foodOfMeal.setNote(foodDTO.getNote());

                // Add to the updated list
                updatedFoodOfMeals.add(foodOfMeal);
            } else {
                // If food not found, return a response with a message
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Food not found: " + foodDTO.getFoodName());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }

        // Set the updated list of FoodOfMeal entities to the meal plan
        existingMealPlan.setFoodOfMeals(updatedFoodOfMeals);

        // Save the updated meal plan to the database
        existingMealPlan = mealPlanRepository.save(existingMealPlan);

        // Return a success message with the updated meal plan
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", "Meal plan updated successfully");
        successResponse.put("mealPlan", existingMealPlan);

        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/delete/{mealPlanId}")
    @Transactional
    public ResponseEntity<Map<String, Object>> deleteMealPlan(@PathVariable int mealPlanId) {
        Optional<MealPlan> mealPlanOpt = mealPlanRepository.findById(mealPlanId);
        if (mealPlanOpt.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Meal plan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        mealPlanRepository.deleteById(mealPlanId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Meal plan deleted successfully");
        return ResponseEntity.ok(response);
    }


}
