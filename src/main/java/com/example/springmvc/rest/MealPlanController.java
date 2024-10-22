package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.CreateMealPlanDTO;
import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.dao.FoodOfMealRespository;
import com.example.springmvc.dao.FoodRespository;
import com.example.springmvc.dao.MealPlanRespository;
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
public class MealPlanController {

    @Autowired
    private MealPlanRespository mealPlanRepository; // Repository để làm việc với bảng mealplan
    @Autowired
    private FoodRespository foodRepository; // Repository để làm việc với bảng food
    @Autowired
    private FoodOfMealRespository foodOfMealRepository; // Repository để làm việc với bảng food_of_meal
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

        // Tìm tất cả các FoodOfMeal liên quan đến mỗi MealPlan
        for (MealPlan mealPlan : mealPlans) {
            List<FoodOfMeal> foodsOfMeal = foodOfMealRepository.findByMealPlan(mealPlan);
            response.put("foodsForMealPlan_" + mealPlan.getMealplan_id(), foodsOfMeal);
        }

        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createMealPlan(@RequestBody CreateMealPlanDTO mealPlanDTO) {
        // Tìm kiếm Client theo tên
        Client client = clientRespository.findByFirstName(mealPlanDTO.getClientName());
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Client not found!"));
        }

        // Tạo một đối tượng MealPlan mới
        MealPlan newMealPlan = new MealPlan();
        newMealPlan.setClient(client);
        newMealPlan.setTraining_status(mealPlanDTO.getTrainingStatus());

        // Lưu vào bảng mealplan
        MealPlan mealPlan = mealPlanRepository.save(newMealPlan);

        // Lưu vào bảng food_of_meal dựa trên tên món ăn
        for (String foodName : mealPlanDTO.getSelectedFoodNames()) {
            Food food = foodRepository.findByFoodName(foodName);
            if (food != null) {
                FoodOfMeal foodOfMeal = new FoodOfMeal();
                foodOfMeal.setMealPlan(mealPlan);
                foodOfMeal.setFood(food);

                foodOfMealRepository.save(foodOfMeal);
            }
        }

        // Trả về thông tin phản hồi dưới dạng Map
        Map<String, Object> response = Map.of(
                "message", "Meal Plan created successfully!",
                "mealplanId", mealPlan.getMealplan_id(),
                "mealPlan", mealPlan
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
