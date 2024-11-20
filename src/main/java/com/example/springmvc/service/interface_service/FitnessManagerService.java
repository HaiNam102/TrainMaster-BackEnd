package com.example.springmvc.service.interface_service;

import com.example.springmvc.entity.FitnessManager;

import java.util.List;
import java.util.Optional;

public interface FitnessManagerService {
    public List<FitnessManager> getAllFitnessManagers();

    public Optional<FitnessManager> getFitnessManagerById(int id);

    public FitnessManager getFitnessManagerByFirstName(String name);

    public FitnessManager addFitnessManager(FitnessManager fitnessManager);

    public FitnessManager updateFitnessManager(FitnessManager fitnessManager);

    public FitnessManager deleteFitnessManagerById(int id);
}
