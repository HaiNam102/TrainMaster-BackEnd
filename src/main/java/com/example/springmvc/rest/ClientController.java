package com.example.springmvc.rest;

import com.example.springmvc.entity.Client;
import com.example.springmvc.service.interface_service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/getAllClient")
    public List<Client> getAllClient(){
        return clientService.getAllClients();
    }

    @GetMapping("/getClientById/{id}")
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable int id){
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()){
            return ResponseEntity.ok(client);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/firstName/{clientId}")
    public ResponseEntity<String> getClientFirstName(@PathVariable int clientId) {
        String clientFirstName = clientService.getClientNameById(clientId);
        if (clientFirstName != null) {
            return ResponseEntity.ok(clientFirstName);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
    }

    @GetMapping("/clientId/{clientName}")
    public ResponseEntity<?> getClientId(@PathVariable String clientName){
        Client client = clientService.getClientByFirstName(clientName);
        if(client != null){
            return ResponseEntity.ok(client);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
    }
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        client.setClient_id(0); // bat buoc them moi va phat sinh ra id
        client = clientService.addClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Client>> updateClient(@PathVariable int id, @RequestBody Client client){
        Optional<Client> existingClient = clientService.getClientById(id);
        if (existingClient.isPresent()){
            existingClient.get().setFirstName(client.getFirstName());
            existingClient.get().setLastName(client.getLastName());
            existingClient.get().setEmail(client.getEmail());
            existingClient.get().setPhone(client.getPhone());
            existingClient.get().setAddress(client.getAddress());
            existingClient.get().setJob(client.getJob());
            existingClient.get().setYears_training(client.getYears_training());
            clientService.updateClient(client);
            return ResponseEntity.ok(existingClient);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteClient(@PathVariable int id){
        Optional<Client> existingClient = clientService.getClientById(id);
        if (existingClient.isPresent()){
            clientService.deleteClientById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
