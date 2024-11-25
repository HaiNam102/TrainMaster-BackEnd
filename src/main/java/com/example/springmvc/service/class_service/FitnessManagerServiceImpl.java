package com.example.springmvc.service.class_service;//package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.ActorRespository.FitnessManagerRespository;
import com.example.springmvc.entity.FitnessManager;
import com.example.springmvc.entity.Login.Account;
import com.example.springmvc.service.interface_service.FitnessManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessManagerServiceImpl implements FitnessManagerService {
    private FitnessManagerRespository fitnessManagerRespository;

    @Autowired
    public FitnessManagerServiceImpl(FitnessManagerRespository fitnessManagerRespository) {
        this.fitnessManagerRespository = fitnessManagerRespository;
    }

    @Override
    public List<FitnessManager> getAllFitnessManagers() {
        return this.fitnessManagerRespository.findAll();
    }

    @Override
    public Optional<FitnessManager> getFitnessManagerById(int id) {
        return this.fitnessManagerRespository.findById(id);
    }

    @Override
    public FitnessManager getFitnessManagerByFirstName(String name) {
        return this.fitnessManagerRespository.findByFirstName(name);
    }

    @Override
    @Transactional
    public FitnessManager addFitnessManager(FitnessManager fitnessManager) {
        return this.fitnessManagerRespository.save(fitnessManager);
    }

    @Override
    @Transactional
    public FitnessManager updateFitnessManager(FitnessManager fitnessManager) {
        return this.fitnessManagerRespository.saveAndFlush(fitnessManager);
    }

    @Override
    @Transactional
    public FitnessManager deleteFitnessManagerById(int id) {
        this.fitnessManagerRespository.deleteById(id);
        return null;
    }

    @Override
    public FitnessManager findFitnessManagerByAccount(Account account) {
        return this.fitnessManagerRespository.findByAccount(account);
    }
}
