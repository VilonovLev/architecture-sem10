package com.vilonov.PetClinic.services;

import com.vilonov.PetClinic.models.Client;
import com.vilonov.PetClinic.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client get(Integer clientId) {
        Optional<Client> optional = clientRepository.findById(clientId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("Incorrect client ID");
    }

    @Transactional
    public boolean addClient(Client client) {
        if (!clientRepository.findAll().contains(client)) {
            clientRepository.save(client);
        }
        return clientRepository.existsById(client.getId());
    }

    @Transactional
    public boolean updateClient(Client client) {
        Optional<Client> optional = clientRepository.findById(client.getId());
        if (optional.isPresent()) {
            return addClient(client) && deleteClient(optional.get());
        }
        return false;
    }

    @Transactional
    public boolean deleteClient(Client client) {
        clientRepository.delete(client);
        return clientRepository.existsById(client.getId());
    }
}
