package com.api.saofranciscoinn.services;

import com.api.saofranciscoinn.models.client.ClientModel;
import com.api.saofranciscoinn.models.room.RoomModel;
import com.api.saofranciscoinn.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {
    final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Transactional
    public RoomModel save (RoomModel roomModel){return roomRepository.save(roomModel);
    }

    public List<RoomModel> findAll(){return roomRepository.findAll();}
    @Transactional
    public void deleteByid(UUID id){roomRepository.deleteById(id);}

    @Transactional
    public void delete(RoomModel roomModel){deleteByid(roomModel.getId());}

    public Optional<RoomModel> findById(UUID id){return  roomRepository.findById(id);}
}
