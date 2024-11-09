package com.example.springmvc.dao;

import com.example.springmvc.entity.Feedback;
import com.example.springmvc.entity.FitnessManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRespository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByFitnessManager(FitnessManager fitnessManager);
}



