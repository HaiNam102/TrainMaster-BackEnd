package com.example.springmvc.dao.ActorRespository;//package com.example.springmvc.dao;

import com.example.springmvc.entity.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTrainerRespository extends JpaRepository<PersonalTrainer,Integer> {
}
