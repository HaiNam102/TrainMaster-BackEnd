package com.example.springmvc.rest;

import com.example.springmvc.dao.FeedbackRespository;
import com.example.springmvc.dao.ActorRespository.FitnessManagerRespository;
import com.example.springmvc.entity.Feedback;
import com.example.springmvc.entity.FitnessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springmvc.DTO_Class.UpdateFeedbackDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:63342")
public class FeedbackController {

    @Autowired
    private FeedbackRespository feedbackRepository;

    @Autowired
    private FitnessManagerRespository fitnessManagerRepository;

    @GetMapping("/getallfeedback")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        if (feedbacks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(feedbacks);
        }
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/fm/{fmId}")
    public ResponseEntity<Map<String, Object>> getFeedbackByFitnessManagerId(@PathVariable int fmId) {
        FitnessManager fitnessManager = fitnessManagerRepository.findById(fmId).orElse(null);
        if (fitnessManager == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Fitness Manager not found!"));
        }

        List<Feedback> feedbacks = feedbackRepository.findByFitnessManager(fitnessManager);
        Map<String, Object> response = new HashMap<>();
        response.put("fitnessManageName", fitnessManager.getFirstName());
        response.put("FeedbackList", feedbacks);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/mealplan")
//    public ResponseEntity<List<Feedback>> getAllFeedbackForMealPlans() {
//        List<Feedback> feedbacks = feedbackRepository.findByMealPlanIsNotNull(); // Fetch feedback linked to meal plans
//        return ResponseEntity.ok(feedbacks);
//    }
//
//    @GetMapping("/program")
//    public ResponseEntity<List<Feedback>> getAllFeedbackForPrograms() {
//        List<Feedback> feedbacks = feedbackRepository.findByProgramIsNotNull(); // Fetch feedback linked to programs
//        return ResponseEntity.ok(feedbacks);
//    }



    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFeedback(@PathVariable("id") int feedbackId,
                                                 @RequestBody UpdateFeedbackDTO updatedFeedbackDTO) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElse(null);
        if (feedback == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Feedback entry not found for ID: " + feedbackId);
        }

        feedback.setFeedback(updatedFeedbackDTO.getFeedback());
        feedback.setApprove(updatedFeedbackDTO.isApprove());

        String fmName = updatedFeedbackDTO.getFitnessManagerName(); // Get the fitness manager's name
        FitnessManager fitnessManager = fitnessManagerRepository.findByFirstName(fmName); // Find by name

        if (fitnessManager != null) {
            feedback.setFitnessManager(fitnessManager); // Set the found fitness manager
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fitness Manager not found for name: " + fmName);
        }

        feedbackRepository.save(feedback);
        return ResponseEntity.ok("Feedback entry updated successfully");
    }




}
