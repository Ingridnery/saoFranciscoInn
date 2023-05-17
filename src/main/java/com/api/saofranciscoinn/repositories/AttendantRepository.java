package com.api.saofranciscoinn.repositories;

import com.api.saofranciscoinn.models.Attendant.AttendantModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendantRepository extends JpaRepository<AttendantModel, UUID> {
}
