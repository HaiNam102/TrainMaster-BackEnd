package com.example.springmvc.dao.PackageRepository;

import com.example.springmvc.entity.TrainingPackage.TrainingPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingPackageRepository extends JpaRepository<TrainingPackage,Integer> {
    public TrainingPackage findByPackageName(String name);

    public TrainingPackage deleteByPackageName(String name);
}
