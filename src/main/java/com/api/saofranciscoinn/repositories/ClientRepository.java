package com.api.saofranciscoinn.repositories;

import com.api.saofranciscoinn.models.client.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    public Optional<ClientModel> findByNameAndSurname(String name, String surname);
}
