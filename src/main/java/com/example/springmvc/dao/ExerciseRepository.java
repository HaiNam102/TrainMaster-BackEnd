package com.example.springmvc.dao;

import com.example.springmvc.entity.Program.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {
    public Exercise findByExerciseName(String name);
}
