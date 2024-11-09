package com.example.springmvc.dao.ActorRespository;//package com.example.springmvc.dao;


import com.example.springmvc.entity.FitnessManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessManagerRespository extends JpaRepository<FitnessManager,Integer> {
    public FitnessManager findByFirstName(String name);
}
