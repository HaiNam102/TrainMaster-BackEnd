package com.example.springmvc.rest;

import com.example.springmvc.dao.AccountRespository;
import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.dao.ClientsTrackingRespository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.clienttracking.ClientsTracking;
import com.example.springmvc.jwt.JwtUtil;
import com.example.springmvc.service.interface_service.ClientsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientstracking")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientsTrackingController {

    private final ClientsTrackingService clientsTrackingService;

    @Autowired
    private ClientRespository clientRespository;
    @Autowired
    private ClientsTrackingRespository clientsTrackingRespository;
    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    public ClientsTrackingController(ClientsTrackingService clientsTrackingService) {
        this.clientsTrackingService = clientsTrackingService;
    }

    // Get all ClientsTracking records
    @GetMapping("/getAllClientsTracking")
    public List<ClientsTracking> getAllClientsTracking() {
        return clientsTrackingService.getAllClientsTracking();
    }

    // Get ClientsTracking by ID
    @GetMapping("/getClientsTrackingById/{id}")
    public ResponseEntity<Optional<ClientsTracking>> getClientsTrackingById(@PathVariable int id) {
        Optional<ClientsTracking> clientsTracking = clientsTrackingService.getClientsTrackingById(id);
        if (clientsTracking.isPresent()) {
            return ResponseEntity.ok(clientsTracking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getClientsTrackingByToken")
    public ResponseEntity<List<ClientsTracking>> getClientsTrackingByToken(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        // Assuming you changed the repository method to return a List
        List<ClientsTracking> clientsTrackingList = clientsTrackingRespository.findAllByUsername(username);

        if (clientsTrackingList.isEmpty()) {
            return ResponseEntity.notFound().build(); // No results found
        } else {
            return ResponseEntity.ok(clientsTrackingList); // Return the list of results
        }
    }

    // Add new ClientsTracking
    @PostMapping
    public ResponseEntity<ClientsTracking> addClientsTracking(@RequestBody ClientsTracking clientsTracking) {
        clientsTracking.setTrackingId(0); // Ensure ID is auto-generated or set to 0
        clientsTracking = clientsTrackingService.addClientsTracking(clientsTracking);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientsTracking);
    }

    // Update ClientsTracking by ID
    @PutMapping("/{id}")
    public ResponseEntity<Optional<ClientsTracking>> updateClientsTracking(
            @PathVariable int id, @RequestBody ClientsTracking clientsTracking) {
        Optional<ClientsTracking> existingTracking = clientsTrackingService.getClientsTrackingById(id);
        if (existingTracking.isPresent()) {
            // Update fields as needed
            existingTracking.get().setWeight(clientsTracking.getWeight());
            existingTracking.get().setSleepHour(clientsTracking.getSleepHour());
            existingTracking.get().setStepCount(clientsTracking.getStepCount());
            existingTracking.get().setNotes(clientsTracking.getNotes());

            clientsTrackingService.updateClientsTracking(existingTracking.get());
            return ResponseEntity.ok(existingTracking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete ClientsTracking by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientsTrackingById(@PathVariable int id) {
        Optional<ClientsTracking> existingTracking = clientsTrackingService.getClientsTrackingById(id);
        if (existingTracking.isPresent()) {
            clientsTrackingService.deleteClientsTrackingById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/client/{clientName}")
    public ResponseEntity<Map<String, Object>> getClientsTrackingByClientName(@PathVariable String clientName) {
        // Tìm Client theo tên
        Client client = clientRespository.findByFirstName(clientName);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Client not found!"));
        }

        // Tìm tất cả ClientsTracking liên quan đến Client này
        List<ClientsTracking> clientsTrackings = clientsTrackingRespository.findByClient(client);

        // Tạo một Map để lưu kết quả phản hồi
        Map<String, Object> response = new HashMap<>();
        response.put("clientName", client.getFirstName());
        response.put("clientsTrackings", clientsTrackings);

        // Nếu cần, có thể thêm các thông tin chi tiết khác cho từng bản ghi ClientsTracking
        for (ClientsTracking tracking : clientsTrackings) {
            // Bạn có thể thêm các thông tin bổ sung nếu cần
            response.put("trackingInfo_" + tracking.getTrackingId(), tracking);
        }

        return ResponseEntity.ok(response);
    }
}
