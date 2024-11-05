package com.example.springmvc.service.interface_service;

import com.example.springmvc.entity.TrainingPackage.TrainingPackage;

import java.util.List;
import java.util.Optional;

public interface TrainingPackageService {
    public List<TrainingPackage> getAllTrainingPackages();

    public Optional<TrainingPackage> getTrainingPackageById(int id);

    public TrainingPackage addTrainingPackage(TrainingPackage trainingPackage);

    public TrainingPackage getTrainingPackageByTrainingPackageName(String name);

    public TrainingPackage updateTrainingPackage(TrainingPackage trainingPackage);

    public TrainingPackage deleteTrainingPackageByTrainingPackageName(String name);
}
