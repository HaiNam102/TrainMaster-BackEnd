package com.example.springmvc.rest.DTOController;

import com.example.springmvc.DTO_Class.CreateClientTrackingDTO;
import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.dao.ClientsTrackingRepository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Tracking.ClientsTracking;
import com.example.springmvc.service.interface_service.ClientsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientstracking")
public class ClientsTrackingController {

    private final ClientsTrackingService clientsTrackingService;

    @Autowired
    private ClientRespository clientRespository;
    @Autowired
    private ClientsTrackingRepository clientsTrackingRespository;


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

    @PostMapping("/create")
    public ResponseEntity<ClientsTracking> addClientsTracking(@RequestBody CreateClientTrackingDTO clientTrackingDTO) {
        // Tìm kiếm Client theo tên
        Client client = clientRespository.findByFirstName(clientTrackingDTO.getClientName());
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body((ClientsTracking) Map.of("message", "Client not found!"));
        }

        ClientsTracking clientsTracking  = new ClientsTracking();
        clientsTracking.setTrackingId(0);
        clientsTracking.setClient(client);
        clientsTracking.setDate(clientTrackingDTO.getDate());
        clientsTracking.setWeight(clientTrackingDTO.getWeight());
        clientsTracking.setSleepHour(clientTrackingDTO.getSleepHour());
        clientsTracking.setStepCount(clientTrackingDTO.getStepCount());
        clientsTracking.setNotes(clientTrackingDTO.getNotes());

        clientsTracking = clientsTrackingService.addClientsTracking(clientsTracking);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientsTracking);
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