package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.CreateCalendarDTO;
import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.dao.CalendarRespository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.entity.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.springmvc.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from your frontend
public class CalendarController {

    @Autowired
    private CalendarRespository calendarRespository;

    @Autowired
    private ClientRespository clientRespository;
    @Autowired
    private JwtUtil jwtUtil;

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

    @GetMapping("/getAllCalendar")
    public ResponseEntity<Map<String, Object>> getAllCalendarEntries() {
        // Lấy danh sách tất cả các mục lịch từ CalendarRespository
        List<Calendar> calendars = calendarRespository.findAll();

        // Nếu danh sách rỗng, trả về phản hồi tương ứng
        if (calendars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(Map.of("message", "No calendar entries found"));
        }

        // Đóng gói kết quả vào Map để trả về JSON
        Map<String, Object> response = new HashMap<>();
        response.put("calendarEntries", calendars);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/getCalendarByUN")
    public ResponseEntity<?> getApprovedCalendar(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        List<CreateCalendarDTO> calendars = calendarRespository.findApprovedCalendarByUsername(username);

        if (calendars.isEmpty()) {
            return ResponseEntity.noContent().build(); // Hoặc bạn có thể trả về thông điệp tùy chỉnh
        }

        // Đảm bảo trả về dữ liệu dưới dạng mảng (một đối tượng có trường calendarEntries là mảng)
        return ResponseEntity.ok(Collections.singletonMap("calendarEntries", calendars));
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
        calendar.setAttendanceStatus(createCalendarDTO.getAttendanceStatus());
        calendar.setClient(client); // assuming clientId is stored in Calendar
        // Save the calendar entry
        calendarRespository.save(calendar);

        return ResponseEntity.ok("Calendar entry created successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAttendanceStatus(
            @PathVariable("id") int calendarId,
            @RequestBody Map<String, Boolean> body) {

        Boolean attendanceStatus = body.get("attendanceStatus");

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
    @DeleteMapping("/delete/{calendarId}")
    public ResponseEntity<String> deleteCalendarEntry(@PathVariable("calendarId") int calendarId) {
        Calendar calendar = calendarRespository.findByCalendarId(calendarId).orElse(null);
        if (calendar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Calendar entry not found for ID: " + calendarId);
        }

        // Xóa bản ghi calendar
        calendarRespository.delete(calendar);

        return ResponseEntity.ok("Calendar entry deleted successfully");
    }



}
