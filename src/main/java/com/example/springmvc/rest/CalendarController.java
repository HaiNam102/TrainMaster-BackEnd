package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.CreateCalendarDTO;
import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.dao.CalendarRespository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
@CrossOrigin(origins = "http://localhost:63342")  // Allow requests from your frontend
public class CalendarController {

    @Autowired
    private CalendarRespository calendarRespository;

    @Autowired
    private ClientRespository clientRespository;

    @GetMapping("/client/{clientName}")
    public ResponseEntity<Map<String, Object>> getCalendarEntriesByClientName(@PathVariable String clientName) {
        // Find Client by name
        Client client = clientRespository.findByFirstName(clientName);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Client not found!"));
        }

        // Find all calendar entries related to this client
        List<Calendar> calendars = calendarRespository.findByClient(client);

        // Create a response map
        Map<String, Object> response = new HashMap<>();
        response.put("clientName", client.getFirstName());
        response.put("calendarEntries", calendars);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCalendarEntry(@RequestBody CreateCalendarDTO createCalendarDTO) {
        // Find the client by name
        Client client = clientRespository.findByFirstName(createCalendarDTO.getClientName());
        if (client == null) {
            return ResponseEntity.badRequest().body("Client not found");
        }

        // Create a new Calendar instance
        Calendar calendar = new Calendar();
        calendar.setDate(createCalendarDTO.getDate());
        calendar.setTimestart(createCalendarDTO.getTimestart());
        calendar.setTimeend(createCalendarDTO.getTimeend());
        calendar.setClient(client); // assuming clientId is stored in Calendar
        // Save the calendar entry
        calendarRespository.save(calendar);

        return ResponseEntity.ok("Calendar entry created successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAttendanceStatus(
            @PathVariable("id") int calendarId,
            @RequestParam("attendanceStatus") Boolean attendanceStatus) {

        // Find the existing Calendar record by ID
        Calendar calendar = calendarRespository.findById(calendarId)
                .orElse(null);
        if (calendar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Calendar entry not found for ID: " + calendarId);
        }

        // Update attendanceStatus
        calendar.setAttendanceStatus(attendanceStatus);

        // Save updated Calendar entry
        calendarRespository.save(calendar);

        return ResponseEntity.ok("Attendance status updated successfully");
    }

}
