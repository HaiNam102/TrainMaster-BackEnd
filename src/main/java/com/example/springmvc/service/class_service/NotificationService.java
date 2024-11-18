package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.FeedbackNotificationRepository;
import com.example.springmvc.dao.Meal_PlanRepository.MealPlanRespository;
import com.example.springmvc.dao.ProgramsRepository.ProgramRepository;
import com.example.springmvc.entity.FeedbackNotification;
import com.example.springmvc.entity.FitnessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private FeedbackNotificationRepository feedbackNotificationRepository;

    @Autowired
    private MealPlanRespository mealPlanRepository;

    @Autowired
    private ProgramRepository programRepository;

    public List<FeedbackNotification> getPendingNotifications() {
        return feedbackNotificationRepository.findByApproveFalseAndIsReadFalse();
    }

    public List<Object[]> getPendingMealPlans() {
        return mealPlanRepository.findPendingMealPlans();
    }

    public List<Object[]> getPendingPrograms() {
        return programRepository.findPendingPrograms();
    }

    // Tìm thông báo theo mealPlanId
    public List<FeedbackNotification> getNotificationsByMealPlanId(int mealPlanId) {
        return feedbackNotificationRepository.findFeedbackNotificationsByMealPlanId(mealPlanId);
    }

//    // Cập nhật thông báo khi FM duyệt
//    public void approveNotification(int id, FitnessManager fitnessManager, String feedback) {
//        FeedbackNotification notification = feedbackNotificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
//        notification.setFitnessManager(fitnessManager);
//        notification.setApprove(true);
//        notification.setFeedback(feedback);
//        feedbackNotificationRepository.save(notification);
//    }

    // Cập nhật trạng thái đã đọc
    public void markAsRead(int id) {
        FeedbackNotification notification = feedbackNotificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        feedbackNotificationRepository.save(notification);
    }
}

