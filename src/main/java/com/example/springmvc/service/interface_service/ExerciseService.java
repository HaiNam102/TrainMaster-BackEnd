package com.example.springmvc.service.interface_service;
import com.example.springmvc.entity.Program.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    public List<Exercise> getAllExercises();

    public Optional<Exercise> getExerciseById(int id);

    public Exercise addExercise(Exercise exercise);

    public Exercise getExerciseByExerciseName(String name);

    public Exercise updateExercise(Exercise exercise);

    public Exercise deleteExerciseByExerciseName(String name);
}
