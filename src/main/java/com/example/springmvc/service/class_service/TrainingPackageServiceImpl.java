package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.PackageRepository.TrainingPackageRepository;
import com.example.springmvc.entity.TrainingPackage.TrainingPackage;
import com.example.springmvc.service.interface_service.TrainingPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingPackageServiceImpl implements TrainingPackageService {
    private TrainingPackageRepository trainingPackageRepository;

    @Autowired
    public TrainingPackageServiceImpl(TrainingPackageRepository trainingPackageRepository) {
        this.trainingPackageRepository = trainingPackageRepository;
    }

    @Override
    public List<TrainingPackage> getAllTrainingPackages() {
        return this.trainingPackageRepository.findAll();
    }

    @Override
    public Optional<TrainingPackage> getTrainingPackageById(int id) {
        return this.trainingPackageRepository.findById(id);
    }

    @Override
    @Transactional
    public TrainingPackage addTrainingPackage(TrainingPackage trainingPackage) {
        return this.trainingPackageRepository.save(trainingPackage);
    }

    @Override
    public TrainingPackage getTrainingPackageByTrainingPackageName(String name) {
        return this.trainingPackageRepository.findByPackageName(name);
    }

    @Override
    @Transactional
    public TrainingPackage updateTrainingPackage(TrainingPackage trainingPackage) {
        return this.trainingPackageRepository.saveAndFlush(trainingPackage);
    }

    @Override
    @Transactional
    public TrainingPackage deleteTrainingPackageByTrainingPackageName(String name) {
        this.trainingPackageRepository.deleteByPackageName(name);
        return null;
    }
}
