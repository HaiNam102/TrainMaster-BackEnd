package com.example.springmvc.rest;

import com.example.springmvc.entity.FitnessManager;
import com.example.springmvc.service.interface_service.FitnessManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fitnessManager")
public class FitnessManagerController {
    private FitnessManagerService fitnessManagerService;

    @Autowired
    public FitnessManagerController(FitnessManagerService fitnessManagerService) {
        this.fitnessManagerService = fitnessManagerService;
    }
    @GetMapping("/getAllFitnessManager")
    public List<FitnessManager> getAllFitnessManager(){
        return fitnessManagerService.getAllFitnessManagers();
    }

    @GetMapping("/getFitnessManagerById/{id}")
    public ResponseEntity<Optional<FitnessManager>> getFitnessManagerById(@PathVariable int id){
        Optional<FitnessManager> fitnessManager = fitnessManagerService.getFitnessManagerById(id);
        if (fitnessManager.isPresent()){
            return ResponseEntity.ok(fitnessManager);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FitnessManager> addFitnessManager(@RequestBody FitnessManager fitnessManager){
        fitnessManager.setFmId(0); // bat buoc them moi va phat sinh ra id
        fitnessManager = fitnessManagerService.addFitnessManager(fitnessManager);
        return ResponseEntity.status(HttpStatus.CREATED).body(fitnessManager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<FitnessManager>> updateFitnessManager(@PathVariable int id, @RequestBody FitnessManager fitnessManager){
        Optional<FitnessManager> existingFitnessManager = fitnessManagerService.getFitnessManagerById(id);
        if (existingFitnessManager.isPresent()){
            existingFitnessManager.get().setFirstName(fitnessManager.getFirstName());
            existingFitnessManager.get().setLastName(fitnessManager.getLastName());
            existingFitnessManager.get().setEmail(fitnessManager.getEmail());
            existingFitnessManager.get().setPhone(fitnessManager.getPhone());
            existingFitnessManager.get().setAddress(fitnessManager.getAddress());
            fitnessManagerService.updateFitnessManager(fitnessManager);
            return ResponseEntity.ok(existingFitnessManager);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteFitnessManager(@PathVariable int id){
        Optional<FitnessManager> existingFitnessManager = fitnessManagerService.getFitnessManagerById(id);
        if (existingFitnessManager.isPresent()){
            fitnessManagerService.deleteFitnessManagerById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
