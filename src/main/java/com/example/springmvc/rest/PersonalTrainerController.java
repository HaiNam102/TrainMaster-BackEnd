package com.example.springmvc.rest;

import com.example.springmvc.entity.PersonalTrainer;
import com.example.springmvc.service.interface_service.PersonalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personalTrainer")
public class PersonalTrainerController {
    private PersonalTrainerService personalTrainerService;

    @Autowired
    public PersonalTrainerController(PersonalTrainerService personalTrainerService) {
        this.personalTrainerService = personalTrainerService;
    }
    @GetMapping("/getAllPersonalTrainer")
    public List<PersonalTrainer> getAllPersonalTrainer(){
        return personalTrainerService.getAllPersonalTrainers();
    }

    @GetMapping("/getPersonalTrainerById/{id}")
    public ResponseEntity<Optional<PersonalTrainer>> getPersonalTrainerById(@PathVariable int id){
        Optional<PersonalTrainer> personalTrainer = personalTrainerService.getPersonalTrainerById(id);
        if (personalTrainer.isPresent()){
            return ResponseEntity.ok(personalTrainer);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonalTrainer> addPersonalTrainer(@RequestBody PersonalTrainer personalTrainer){
        personalTrainer.setPt_id(0); // bat buoc them moi va phat sinh ra id
        personalTrainer = personalTrainerService.addPersonalTrainer(personalTrainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(personalTrainer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<PersonalTrainer>> updatePersonalTrainer(@PathVariable int id, @RequestBody PersonalTrainer personalTrainer){
        Optional<PersonalTrainer> existingPersonalTrainer = personalTrainerService.getPersonalTrainerById(id);
        if (existingPersonalTrainer.isPresent()){
            existingPersonalTrainer.get().setFirstName(personalTrainer.getFirstName());
            existingPersonalTrainer.get().setLastName(personalTrainer.getLastName());
            existingPersonalTrainer.get().setEmail(personalTrainer.getEmail());
            existingPersonalTrainer.get().setPhone(personalTrainer.getPhone());
            existingPersonalTrainer.get().setAddress(personalTrainer.getAddress());
            existingPersonalTrainer.get().setReport(personalTrainer.getReport());
            personalTrainerService.updatePersonalTrainer(personalTrainer);
            return ResponseEntity.ok(existingPersonalTrainer);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletePersonalTrainer(@PathVariable int id){
        Optional<PersonalTrainer> existingPersonalTrainer = personalTrainerService.getPersonalTrainerById(id);
        if (existingPersonalTrainer.isPresent()){
            personalTrainerService.deletePersonalTrainerById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
