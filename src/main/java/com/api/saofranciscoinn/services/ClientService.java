package com.api.saofranciscoinn.services;

import com.api.saofranciscoinn.models.client.ClientModel;
import com.api.saofranciscoinn.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientModel save (ClientModel clientModel){
        return clientRepository.save(clientModel);
    }
    public List<ClientModel> findAll(){return clientRepository.findAll();}
    @Transactional
    public void deleteByid(UUID id){clientRepository.deleteById(id);}

    @Transactional
    public void delete(ClientModel clienteModel){deleteByid(clienteModel.getId());}

    public Optional<ClientModel> findById(UUID id){return  clientRepository.findById(id);}

    public Optional<ClientModel> findByNameAndSurname(String name, String surname){return clientRepository.findByNameAndSurname(name,surname);}

}
