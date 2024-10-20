package com.example.springmvc.rest;


import com.example.springmvc.entity.MealPlan.Food;
import com.example.springmvc.service.interface_service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodController {
    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    @GetMapping("/getAllFood")
    public List<Food> getAllFood(){
        return foodService.getAllFoods();
    }

    @GetMapping("/getFoodById/{id}")
    public ResponseEntity<Optional<Food>> getFoodById(@PathVariable int id){
        Optional<Food> food = foodService.getFoodById(id);
        if (food.isPresent()){
            return ResponseEntity.ok(food);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getFoodByFoodName/{name}")
    public ResponseEntity<Food> getFoodByFoodName(@PathVariable String name){
        Food food = foodService.getFoodByFoodName(name);
        if (food != null){
            return ResponseEntity.ok(food);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Food> addFood(@RequestBody Food food){
        food.setFoodId(0); // bat buoc them moi va phat sinh ra id
        food = foodService.addFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(food);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Food> updateFood(@PathVariable String name, @RequestBody Food updatedFood) {
        Food existingFood = foodService.getFoodByFoodName(name);

        if (existingFood != null) {
            existingFood.setFoodName(updatedFood.getFoodName());
            existingFood.setNotes(updatedFood.getNotes());
            existingFood.setKcal(updatedFood.getKcal());
            existingFood.setProtein(updatedFood.getProtein());
            existingFood.setCarb(updatedFood.getCarb());
            existingFood.setFat(updatedFood.getFat());

            foodService.updateFood(existingFood);
            return ResponseEntity.ok(existingFood);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{name}")
    public  ResponseEntity<Void> deleteFood(@PathVariable String name){
        Food existingFood = foodService.getFoodByFoodName(name);
        if (existingFood != null){
            foodService.deleteFoodByFoodName(name);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
