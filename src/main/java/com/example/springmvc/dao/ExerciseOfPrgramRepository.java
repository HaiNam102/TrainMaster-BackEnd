package com.example.springmvc.dao;

import com.example.springmvc.entity.Program.ExerciseOfProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseOfPrgramRepository extends JpaRepository<ExerciseOfProgram,Integer> {
}
