package com.egg.library.services;

import com.egg.library.models.Client;
import com.egg.library.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
    public void createClient(Long document, String firstName, String lastName, String phone) {
        Client client = new Client();
        client.setDocument(document);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhone(phone);
        repository.save(client);
    }

    @Transactional(readOnly = true)
    public Client findClient(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void modify(Long id, Long document, String firstName, String lastName, String phone) {
        Client client = repository.findById(id).orElse(null);
        if (client != null) {
            client.setDocument(document);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setPhone(phone);
            repository.save(client);
        }
    }

    @Transactional
    public void deleteClient(Long id) {
        repository.deleteById(id);
    }
}
