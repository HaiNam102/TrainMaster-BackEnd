package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.ProgramsRepository.ExerciseRepository;
import com.example.springmvc.entity.Program.Exercise;
import com.example.springmvc.service.interface_service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private ExerciseRepository excerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRespository) {
        this.excerciseRepository = exerciseRespository;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return this.excerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> getExerciseById(int id) {
        return this.excerciseRepository.findById(id);
    }

    @Override
    @Transactional
    public Exercise addExercise(Exercise Exercise) {
        return this.excerciseRepository.save(Exercise);
    }

    @Override
    @Transactional
    public Exercise getExerciseByExerciseName(String name) {
        return this.excerciseRepository.findByExerciseName(name);
    }

    @Override
    @Transactional
    public Exercise updateExercise(Exercise Exercise) {
        return this.excerciseRepository.saveAndFlush(Exercise);
    }

    @Override
    @Transactional
    public Exercise deleteExerciseByExerciseName(String name) {
        this.excerciseRepository.deleteByExerciseName(name);
        return null;
    }
}
