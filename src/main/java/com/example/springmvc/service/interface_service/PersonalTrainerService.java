package com.example.springmvc.service.interface_service;

import com.example.springmvc.entity.PersonalTrainer;

import java.util.List;
import java.util.Optional;

public interface PersonalTrainerService {
    public List<PersonalTrainer> getAllPersonalTrainers();

    public Optional<PersonalTrainer> getPersonalTrainerById(int id);

    public PersonalTrainer addPersonalTrainer(PersonalTrainer personalTrainer);

    public PersonalTrainer updatePersonalTrainer(PersonalTrainer personalTrainer);

    public PersonalTrainer deletePersonalTrainerById(int id);
}
