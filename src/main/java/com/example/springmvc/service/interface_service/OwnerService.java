package com.example.springmvc.service.interface_service;

import com.example.springmvc.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    public List<Owner> getAllOwners();

    public Optional<Owner> getOwnerById(int id);

    public Owner addOwner(Owner owner);

    public Owner updateOwner(Owner owner);

    public Owner deleteOwnerById(int id);
}
