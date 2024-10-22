package com.example.springmvc.service.class_service;//package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.ActorRespository.PersonalTrainerRespository;
import com.example.springmvc.entity.PersonalTrainer;
import com.example.springmvc.service.interface_service.PersonalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalTrainerServiceImpl implements PersonalTrainerService {
    private PersonalTrainerRespository personalTrainerRespository;

    @Autowired
    public PersonalTrainerServiceImpl(PersonalTrainerRespository personalTrainerRespository) {
        this.personalTrainerRespository = personalTrainerRespository;
    }

    @Override
    public List<PersonalTrainer> getAllPersonalTrainers() {
        return this.personalTrainerRespository.findAll();
    }

    @Override
    public Optional<PersonalTrainer> getPersonalTrainerById(int id) {
        return this.personalTrainerRespository.findById(id);
    }

    @Override
    @Transactional
    public PersonalTrainer addPersonalTrainer(PersonalTrainer personalTrainer) {
        return this.personalTrainerRespository.save(personalTrainer);
    }

    @Override
    @Transactional
    public PersonalTrainer updatePersonalTrainer(PersonalTrainer personalTrainer) {
        return this.personalTrainerRespository.saveAndFlush(personalTrainer);
    }

    @Override
    @Transactional
    public PersonalTrainer deletePersonalTrainerById(int id) {
        this.personalTrainerRespository.deleteById(id);
        return null;
    }
}
