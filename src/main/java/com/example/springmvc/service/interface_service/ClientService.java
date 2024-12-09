package com.example.springmvc.service.interface_service;

import com.example.springmvc.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    public List<Client> getAllClients();

    public Optional<Client> getClientById(int id);

    public Client addClient(Client client);

    public Client updateClient(Client client);

    public void deleteClientById(int id);

    public String getClientNameById(int id);

    public Client getClientByFirstName(String name);

    public Optional<Client> getClientByEmail(String email);
}
