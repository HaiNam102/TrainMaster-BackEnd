package com.example.springmvc.dao.ProgramsRepository;

import com.example.springmvc.entity.Program.Exercise;
import com.example.springmvc.entity.Program.ExerciseOfProgram;
import com.example.springmvc.entity.Program.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseOfProgramRepository extends JpaRepository<ExerciseOfProgram,Integer> {
//    ExerciseOfProgram findByProgramAndExercises(Program program, Exercise exercise);
    Optional<ExerciseOfProgram> findByProgram_ProgramIdAndExercise_ExerciseId(int programId, int exerciseId);
}
