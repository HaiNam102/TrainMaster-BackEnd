package com.example.springmvc.dao.ProgramsRepository;

import com.example.springmvc.entity.Program.Exercise;
import com.example.springmvc.entity.Program.ExerciseOfProgram;
import com.example.springmvc.entity.Program.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseOfPrgramRepository extends JpaRepository<ExerciseOfProgram,Integer> {
    ExerciseOfProgram findByProgramAndExercises(Program program, Exercise exercise);
}
