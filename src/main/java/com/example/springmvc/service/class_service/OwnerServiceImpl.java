package com.example.springmvc.service.class_service;//package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.OwnerRespository;
import com.example.springmvc.entity.Owner;
import com.example.springmvc.service.interface_service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    private OwnerRespository ownerRespository;

    @Autowired
    public OwnerServiceImpl(OwnerRespository ownerRespository) {
        this.ownerRespository = ownerRespository;
    }

    @Override
    public List<Owner> getAllOwners() {
        return this.ownerRespository.findAll();
    }

    @Override
    public Optional<Owner> getOwnerById(int id) {
        return this.ownerRespository.findById(id);
    }

    @Override
    @Transactional
    public Owner addOwner(Owner owner) {
        return this.ownerRespository.save(owner);
    }

    @Override
    @Transactional
    public Owner updateOwner(Owner owner) {
        return this.ownerRespository.saveAndFlush(owner);
    }

    @Override
    @Transactional
    public Owner deleteOwnerById(int id) {
        this.ownerRespository.deleteById(id);
        return null;
    }
}
