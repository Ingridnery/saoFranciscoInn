package com.api.saofranciscoinn.repositories;

import com.api.saofranciscoinn.models.room.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface RoomRepository extends JpaRepository<RoomModel, UUID>
{
}
