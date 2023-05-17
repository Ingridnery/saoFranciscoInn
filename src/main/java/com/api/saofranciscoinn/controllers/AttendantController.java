package com.api.saofranciscoinn.controllers;

import com.api.saofranciscoinn.dtos.AttendantDto;
import com.api.saofranciscoinn.models.Attendant.AttendantModel;
import com.api.saofranciscoinn.services.AttendantService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("attendant")
public class AttendantController {
    final AttendantService attendantService;

    public AttendantController(AttendantService attendantService) {
        this.attendantService = attendantService;
    }
    @PostMapping(value="/register")
    public ResponseEntity<Object> saveAttendant(@Valid @RequestBody AttendantDto attendantDto){
        var attendantModel = new AttendantModel();
        BeanUtils.copyProperties(attendantDto, attendantModel);
        if(attendantService.checkIfExists(attendantModel.getUsername()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe");
        return ResponseEntity.status(HttpStatus.CREATED).body(attendantService.save(attendantModel));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AttendantDto attendantDto){
        AttendantModel inDBAttendant = attendantService.findByUsername(attendantDto.getUsername()).get();
        if(inDBAttendant.getPassword().equals(attendantDto.getPassword())){
            return ResponseEntity.status(HttpStatus.OK).body(inDBAttendant.getId());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Dados incorretos!");
    }


}
