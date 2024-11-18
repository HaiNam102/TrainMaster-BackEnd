package com.example.springmvc.rest;

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
            existingExercise.get().setMuscleGroup(updatedExercise.getMuscleGroup()); // Set muscle group
            exerciseService.updateExercise(existingExercise.orElse(null));
            return ResponseEntity.ok(existingExercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{name}")
    public  ResponseEntity<Void> deleteExercise(@PathVariable String name){
        Optional<Exercise> existingExercise = exerciseService.getExerciseByExerciseName(name);
        if (existingExercise.isPresent()){
            exerciseService.deleteExerciseByExerciseName(name);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
