package com.example.springmvc.dao;

import com.example.springmvc.entity.FeedbackNotification;
import com.example.springmvc.entity.FitnessManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackNotificationRepository extends JpaRepository<FeedbackNotification, Integer> {
    List<FeedbackNotification> findByApproveFalseAndIsReadFalse();

    @Query("SELECT fn FROM FeedbackNotification fn JOIN fn.mealPlan mp WHERE mp.id = :mealplanId")
    List<FeedbackNotification> findFeedbackNotificationsByMealPlanId(@Param("mealplanId") int mealplanId);

    @Query("SELECT fn FROM FeedbackNotification fn JOIN fn.program mp WHERE mp.id = :programId")
    List<FeedbackNotification> findFeedbackNotificationsByProgramId(@Param("programId") int programId);

}