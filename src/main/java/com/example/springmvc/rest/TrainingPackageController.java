package com.example.springmvc.rest;

import com.example.springmvc.entity.TrainingPackage.TrainingPackage;
import com.example.springmvc.service.interface_service.TrainingPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/package")
public class TrainingPackageController {
    private TrainingPackageService trainingPackageService;

    @Autowired
    public TrainingPackageController(TrainingPackageService trainingPackageService) {
        this.trainingPackageService = trainingPackageService;
    }

    @GetMapping("/getAllTrainingPackage")
    public List<TrainingPackage> getAllTrainingPackage(){
        return trainingPackageService.getAllTrainingPackages();
    }

    @GetMapping("/getTrainingPackageById/{id}")
    public ResponseEntity<Optional<TrainingPackage>> getTrainingPackageById(@PathVariable int id){
        Optional<TrainingPackage> TrainingPackage = trainingPackageService.getTrainingPackageById(id);
        if (TrainingPackage.isPresent()){
            return ResponseEntity.ok(TrainingPackage);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getTrainingPackageByTrainingPackageName/{name}")
    public ResponseEntity<TrainingPackage> getTrainingPackageByTrainingPackageName(@PathVariable String name){
        TrainingPackage trainingPackage = trainingPackageService.getTrainingPackageByTrainingPackageName(name);
        if (trainingPackage != null){
            return ResponseEntity.ok(trainingPackage);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TrainingPackage> addTrainingPackage(@RequestBody TrainingPackage trainingPackage){
        trainingPackage.setPackage_id(0); // bat buoc them moi va phat sinh ra id
        trainingPackage = trainingPackageService.addTrainingPackage(trainingPackage);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingPackage);
    }

    @PutMapping("/{name}")
    public ResponseEntity<TrainingPackage> updateTrainingPackage(@PathVariable String name, @RequestBody TrainingPackage updatedTrainingPackage) {
        TrainingPackage existingTrainingPackage = trainingPackageService.getTrainingPackageByTrainingPackageName(name);

        if (existingTrainingPackage != null) {
            existingTrainingPackage.setPackageName(updatedTrainingPackage.getPackageName());
            existingTrainingPackage.setCost(updatedTrainingPackage.getCost());
            existingTrainingPackage.setSessionNumber(updatedTrainingPackage.getSessionNumber());
            existingTrainingPackage.setDescription(updatedTrainingPackage.getDescription());

            trainingPackageService.updateTrainingPackage(existingTrainingPackage);
            return ResponseEntity.ok(existingTrainingPackage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{name}")
    public  ResponseEntity<Void> deleteTrainingPackage(@PathVariable String name){
        TrainingPackage existingTrainingPackage = trainingPackageService.getTrainingPackageByTrainingPackageName(name);
        if (existingTrainingPackage != null){
            trainingPackageService.deleteTrainingPackageByTrainingPackageName(name);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
