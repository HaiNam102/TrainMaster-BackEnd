package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.ClientsTrackingRepository;
import com.example.springmvc.entity.Tracking.ClientsTracking;
import com.example.springmvc.service.interface_service.ClientsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsTrackingServiceImpl implements ClientsTrackingService {

    private final ClientsTrackingRepository clientsTrackingRepository;

    @Autowired
    public ClientsTrackingServiceImpl(ClientsTrackingRepository clientsTrackingRepository) {
        this.clientsTrackingRepository = clientsTrackingRepository;
    }

    @Override
    public List<ClientsTracking> getAllClientsTracking() {
        return this.clientsTrackingRepository.findAll();
    }

    @Override
    @Transactional
    public ClientsTracking addClientsTracking(ClientsTracking clientsTracking) {
        return this.clientsTrackingRepository.save(clientsTracking);
    }

    @Override
    @Transactional
    public ClientsTracking updateClientsTracking(ClientsTracking clientsTracking) {
        return this.clientsTrackingRepository.saveAndFlush(clientsTracking);
    }

    @Override
    @Transactional
    public ClientsTracking deleteClientsTrackingById(int id) {
        this.clientsTrackingRepository.deleteById(id);
        return null;
    }

    @Override
    public Optional<ClientsTracking> getClientsTrackingById(int id) {
        return this.clientsTrackingRepository.findById(id);
    }
}
