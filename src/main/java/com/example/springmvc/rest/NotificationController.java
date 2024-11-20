package com.example.springmvc.rest;

import com.example.springmvc.DTO_Class.ApprovalRequest;
import com.example.springmvc.dao.ActorRespository.FitnessManagerRespository;
import com.example.springmvc.dao.FeedbackNotificationRepository;
import com.example.springmvc.entity.FeedbackNotification;
import com.example.springmvc.entity.FitnessManager;
import com.example.springmvc.service.class_service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {
    @Autowired
    private FeedbackNotificationRepository feedbackNotificationRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private FitnessManagerRespository fitnessManagerRepository;

    @GetMapping
    public List<FeedbackNotification> getNotifications() {
        return notificationService.getPendingNotifications();
    }

    // Duyệt thông báo
    @PutMapping("/approve/{mealPlanId}")
    public ResponseEntity<Void> approveNotificationMealPlan(
            @PathVariable int mealPlanId,
            @RequestBody ApprovalRequest approvalRequest) {

        // Tìm FeedbackNotification dựa trên mealPlanId
        List<FeedbackNotification> feedbackNotifications = notificationService.getNotificationsByMealPlanId(mealPlanId);

        if (feedbackNotifications.isEmpty()) {
            throw new RuntimeException("No FeedbackNotification found for MealPlan ID: " + mealPlanId);
        }

        // Giả sử chỉ có một FeedbackNotification cho mỗi MealPlan, lấy phần tử đầu tiên
        FeedbackNotification feedbackNotification = feedbackNotifications.get(0);

        // Lấy FitnessManager dựa trên tên của FitnessManager (fmName)
        FitnessManager fitnessManager = fitnessManagerRepository.findByFirstName(approvalRequest.getFmName());
        if (fitnessManager == null) {
            throw new RuntimeException("Fitness Manager not found");
        }

        // Cập nhật các trường trong FeedbackNotification
        feedbackNotification.setFeedback(approvalRequest.getFeedback());
        feedbackNotification.setApprove(true); // Đánh dấu là đã duyệt
        feedbackNotification.setFitnessManager(fitnessManager); // Gán FitnessManager

        // Lưu lại cập nhật
        feedbackNotificationRepository.save(feedbackNotification);

        return ResponseEntity.ok().build();
    }
    @PutMapping("/approve/program/{programId}")
    public ResponseEntity<Void> approveNotificationProgram(
            @PathVariable int programId,
            @RequestBody ApprovalRequest approvalRequest) {

        // Tìm FeedbackNotification dựa trên mealPlanId
        List<FeedbackNotification> feedbackNotifications = notificationService.getNotificationsByProgramId(programId);

        if (feedbackNotifications.isEmpty()) {
            throw new RuntimeException("No FeedbackNotification found for Program ID: " + programId);
        }

        // Giả sử chỉ có một FeedbackNotification cho mỗi MealPlan, lấy phần tử đầu tiên
        FeedbackNotification feedbackNotification = feedbackNotifications.get(0);

        // Lấy FitnessManager dựa trên tên của FitnessManager (fmName)
        FitnessManager fitnessManager = fitnessManagerRepository.findByFirstName(approvalRequest.getFmName());
        if (fitnessManager == null) {
            throw new RuntimeException("Fitness Manager not found");
        }

        // Cập nhật các trường trong FeedbackNotification
        feedbackNotification.setFeedback(approvalRequest.getFeedback());
        feedbackNotification.setApprove(true); // Đánh dấu là đã duyệt
        feedbackNotification.setFitnessManager(fitnessManager); // Gán FitnessManager

        // Lưu lại cập nhật
        feedbackNotificationRepository.save(feedbackNotification);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/mealPlan/{mealPlanId}")
    public ResponseEntity<List<FeedbackNotification>> getNotificationsByMealPlanId(@PathVariable("mealPlanId") int mealPlanId) {
        try {
            List<FeedbackNotification> notifications = notificationService.getNotificationsByMealPlanId(mealPlanId);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // Đánh dấu thông báo là đã đọc
    @PutMapping("/read/{mealPlanId}")
    public ResponseEntity<Void> markAsRead(@PathVariable int mealPlanId) {
        // Tìm FeedbackNotification dựa trên mealPlanId
        List<FeedbackNotification> feedbackNotifications = feedbackNotificationRepository.findFeedbackNotificationsByMealPlanId(mealPlanId);

        if (feedbackNotifications.isEmpty()) {
            throw new RuntimeException("No FeedbackNotification found for MealPlan ID: " + mealPlanId);
        }

        // Cập nhật trạng thái 'isRead' cho từng FeedbackNotification tìm được
        for (FeedbackNotification feedbackNotification : feedbackNotifications) {
            feedbackNotification.setRead(true); // Đánh dấu là đã đọc
            feedbackNotificationRepository.save(feedbackNotification); // Lưu lại cập nhật
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{mealPlanId}")
    public ResponseEntity<Void> deleteNotificationByMealPlanId(@PathVariable int mealPlanId) {
        // Tìm tất cả các FeedbackNotification dựa trên mealPlanId
        List<FeedbackNotification> feedbackNotifications = feedbackNotificationRepository.findFeedbackNotificationsByMealPlanId(mealPlanId);

        if (feedbackNotifications.isEmpty()) {
            throw new RuntimeException("No FeedbackNotification found for MealPlan ID: " + mealPlanId);
        }

        // Xóa tất cả các FeedbackNotification tìm được
        feedbackNotificationRepository.deleteAll(feedbackNotifications);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/program/{programId}")
    public ResponseEntity<Void> deleteNotificationByProgramId(@PathVariable int programId) {
        // Tìm tất cả các FeedbackNotification dựa trên mealPlanId
        List<FeedbackNotification> feedbackNotifications = feedbackNotificationRepository.findFeedbackNotificationsByProgramId(programId);

        if (feedbackNotifications.isEmpty()) {
            throw new RuntimeException("No FeedbackNotification found for MealPlan ID: " + programId);
        }

        // Xóa tất cả các FeedbackNotification tìm được
        feedbackNotificationRepository.deleteAll(feedbackNotifications);

        return ResponseEntity.ok().build();
    }

}

