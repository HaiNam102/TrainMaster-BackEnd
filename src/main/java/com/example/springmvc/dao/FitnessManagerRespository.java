package com.example.springmvc.dao;//package com.example.springmvc.dao;

import com.example.springmvc.entity.FitnessManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessManagerRespository extends JpaRepository<FitnessManager,Integer> {
}
