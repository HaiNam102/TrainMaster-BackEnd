package com.example.springmvc.rest;

import com.example.springmvc.entity.MealPlan.Food;
import com.example.springmvc.entity.Program.Exercise;
import com.example.springmvc.service.interface_service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
@CrossOrigin(origins = "http://localhost:3000")
public class ExerciseController {
    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/getAllExercise")
    public List<Exercise> getAllExercise(){
        return exerciseService.getAllExercises();
    }

    @GetMapping("/getExerciseById/{id}")
    public ResponseEntity<Optional<Exercise>> getExerciseById(@PathVariable int id){
        Optional<Exercise> exercise = exerciseService.getExerciseById(id);
        if (exercise.isPresent()){
            return ResponseEntity.ok(exercise);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getExerciseByExerciseName/{name}")
    public ResponseEntity<Optional<Exercise>> getExerciseByExerciseName(@PathVariable String name){
        Optional<Exercise> exercise = exerciseService.getExerciseByExerciseName(name);
        if (exercise.isPresent()){
            return ResponseEntity.ok(exercise);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise){
        exercise.setExerciseId(0); // bat buoc them moi va phat sinh ra id
        exercise = exerciseService.addExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(exercise);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Optional<Exercise>> updateExercise(@PathVariable String name, @RequestBody Exercise updatedExercise) {
        Optional<Exercise> existingExercise = exerciseService.getExerciseByExerciseName(name);

        if (existingExercise.isPresent()) {
            existingExercise.get().setExercisename(updatedExercise.getExercisename());
            exerciseService.updateExercise(existingExercise.orElse(null));
            return ResponseEntity.ok(existingExercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateExercise/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable int id, @RequestBody Exercise exercise){
        Optional<Exercise> existingExercise = exerciseService.getExerciseById(id);
        if (existingExercise.isPresent()){
            Exercise updatedExercise = existingExercise.get();
            updatedExercise.setExercisename(exercise.getExercisename());
            exerciseService.updateExercise(updatedExercise);
            return ResponseEntity.ok(updatedExercise);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteExercise/{id}")
    public  ResponseEntity<Void> deleteExercise(@PathVariable int id){
        Optional<Exercise> existingExercise = exerciseService.getExerciseById(id);
        if (existingExercise.isPresent()){
            exerciseService.deleteExerciseByExerciseId(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
