package com.example.springmvc.rest;

import com.example.springmvc.entity.MealPlan.Food;
import com.example.springmvc.service.interface_service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "http://localhost:3000")
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

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable int id, @RequestBody Food food){
        Optional<Food> existingFood = foodService.getFoodById(id);
        if (existingFood.isPresent()){
            Food updatedFood = existingFood.get();  // Lấy đối tượng thực tế từ Optional
            updatedFood.setFoodName(food.getFoodName());
            updatedFood.setNotes(food.getNotes());
            updatedFood.setKcal(food.getKcal());
            updatedFood.setProtein(food.getProtein());
            updatedFood.setCarb(food.getCarb());
            updatedFood.setFat(food.getFat());

            foodService.updateFood(updatedFood);  // Cập nhật đối tượng đã thay đổi
            return ResponseEntity.ok(updatedFood);  // Trả về đối tượng đã được cập nhật
        }else{
            return ResponseEntity.notFound().build();  // Nếu không tìm thấy thực phẩm, trả về 404
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteFood(@PathVariable int id){
        Optional<Food> existingFood = FoodService.getFoodById(id);
        if (existingFood.isPresent()){
            FoodService.deleteFoodById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
