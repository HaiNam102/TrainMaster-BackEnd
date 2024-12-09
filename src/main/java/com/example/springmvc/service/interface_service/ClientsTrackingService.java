package com.example.springmvc.service.interface_service;

import com.example.springmvc.entity.clienttracking.ClientsTracking;

import java.util.List;
import java.util.Optional;

public interface ClientsTrackingService {

    public List<ClientsTracking> getAllClientsTracking();

    public ClientsTracking addClientsTracking(ClientsTracking clientsTracking);

    public ClientsTracking updateClientsTracking(ClientsTracking clientsTracking);

    public ClientsTracking deleteClientsTrackingById(int id);

    public Optional<ClientsTracking> getClientsTrackingById(int id);

}
