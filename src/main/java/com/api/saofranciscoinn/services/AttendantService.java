package com.api.saofranciscoinn.services;

import com.api.saofranciscoinn.models.Attendant.AttendantModel;
import com.api.saofranciscoinn.repositories.AttendantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AttendantService {

    final AttendantRepository attendantRepository;

    public AttendantService(AttendantRepository attendantRepository){this.attendantRepository=attendantRepository;}

    @Transactional
    public AttendantModel save(AttendantModel attendantModel){
        return attendantRepository.save(attendantModel);
    }
    public Optional<AttendantModel> findByUsername(String username){
        return attendantRepository.findAll().stream()
                .filter(attendant -> attendant.getUsername().equals(username))
                .findFirst();
    }

    public boolean checkIfExists(String username) {
        return findByUsername(username).isPresent();
    }
}
