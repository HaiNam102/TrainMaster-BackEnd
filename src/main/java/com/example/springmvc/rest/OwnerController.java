package com.example.springmvc.rest;

import com.example.springmvc.entity.Owner;
import com.example.springmvc.service.interface_service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @GetMapping("/getAllOwner")
    public List<Owner> getAllOwner(){
        return ownerService.getAllOwners();
    }

    @GetMapping("/getOwnerById/{id}")
    public ResponseEntity<Optional<Owner>> getOwnerById(@PathVariable int id){
        Optional<Owner> owner = ownerService.getOwnerById(id);
        if (owner.isPresent()){
            return ResponseEntity.ok(owner);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
//
//    @PostMapping
//    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner){
//        owner.setOwnerId(0); // bat buoc them moi va phat sinh ra id
//        owner = ownerService.addOwner(owner);
//        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Owner>> updateOwner(@PathVariable int id, @RequestBody Owner owner){
        Optional<Owner> existingOwner = ownerService.getOwnerById(id);
        if (existingOwner.isPresent()){
            Owner ownerToUpdate = existingOwner.get();

            ownerToUpdate.setFirstName(owner.getFirstName());
            ownerToUpdate.setLastName(owner.getLastName());
            ownerToUpdate.setEmail(owner.getEmail());

            ownerService.updateOwner(ownerToUpdate);

            return ResponseEntity.ok(existingOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//    @DeleteMapping("/{id}")
//    public  ResponseEntity<Void> deleteOwner(@PathVariable int id){
//        Optional<Owner> existingOwner = ownerService.getOwnerById(id);
//        if (existingOwner.isPresent()){
//            ownerService.deleteOwnerById(id);
//            return ResponseEntity.ok().build();
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
}
