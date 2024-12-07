package com.example.springmvc.service.class_service;//package com.example.springmvc.service.class_service;

import com.example.springmvc.dao.ActorRespository.ClientRespository;
import com.example.springmvc.entity.Client;
import com.example.springmvc.service.interface_service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRespository clientRespository;

    @Autowired
    public ClientServiceImpl(ClientRespository clientRespository) {
        this.clientRespository = clientRespository;
    }

    @Override
    public List<Client> getAllClients() {
        return this.clientRespository.findAll();
    }

    @Override
    public Optional<Client> getClientById(int id) {
        return this.clientRespository.findById(id);
    }

    @Override
    @Transactional
    public Client addClient(Client client) {
        return this.clientRespository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        return this.clientRespository.saveAndFlush(client);
    }

    @Override
    @Transactional
    public Client deleteClientById(int id) {
        this.clientRespository.deleteById(id);
        return null;
    }

    @Override
    public String getClientNameById(int id) {
        Optional<Client> client = clientRespository.findById(id);
        return client.map(Client::getFirstName).orElse(null);
    }

    @Override
    public Client getClientByFirstName(String name) {
        return this.clientRespository.findByFirstName(name);
    }

    @Override
    public Optional<Client> getClientByEmail(String email) {
        return this.clientRespository.getClientByEmail(email);
    }
}
