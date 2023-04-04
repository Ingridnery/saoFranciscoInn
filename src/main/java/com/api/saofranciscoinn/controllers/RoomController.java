package com.api.saofranciscoinn.controllers;
import com.api.saofranciscoinn.dtos.RoomDto;
import com.api.saofranciscoinn.models.room.RoomModel;
import com.api.saofranciscoinn.services.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/room")
public class RoomController {

    final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveRoom(@Valid @RequestBody RoomDto roomDto){
        var roomModel= new RoomModel();
        BeanUtils.copyProperties(roomDto,roomModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.save(roomModel));
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateRoom(@PathVariable UUID id, @RequestBody RoomDto RoomDto){
        Optional<RoomModel> roomModelOptional= roomService.findById(id);
        if(roomModelOptional.isPresent()){
            var roomModel = roomModelOptional.get();
            roomModel.setRoomType(RoomDto.getRoomType());
            roomModel.setDailyValue(RoomDto.getDailyValue());
            roomModel.setBedType(RoomDto.getBedType());
            return ResponseEntity.status(HttpStatus.OK).body(roomService.save(roomModel));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roomService.findAll());
    }
    @GetMapping(value="/findBy/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value="id")UUID id){
        Optional<RoomModel> roomModelOptional = roomService.findById(id);
        return roomModelOptional.<ResponseEntity<Object>>map(clientModel -> ResponseEntity.status(HttpStatus.OK).body(clientModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roomService.findAll()));
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id")UUID id){
        Optional<RoomModel> roomModelOptional = roomService.findById(id);
        if(roomModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        //verificar quarto esta em uma reserva
        else
           roomService.delete(roomModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");

    }
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<RoomModel>> getAllRooms(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findAll());
    }
}
