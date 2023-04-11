package com.api.saofranciscoinn.controllers;

import com.api.saofranciscoinn.dtos.ClientDto;
import com.api.saofranciscoinn.models.booking.BookingModel;
import com.api.saofranciscoinn.models.client.ClientModel;
import com.api.saofranciscoinn.services.BookingService;
import com.api.saofranciscoinn.services.ClientService;
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
@CrossOrigin(origins = "*", maxAge =3600)
@RequestMapping("client")
public class ClientController {
    final ClientService clientService;
    final BookingService bookingService;

    public ClientController(ClientService clientService, BookingService bookingService) {
        this.clientService = clientService;
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveClient(@Valid @RequestBody ClientDto clientDto){
        var clientModel= new ClientModel();
        BeanUtils.copyProperties(clientDto,clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));

    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable UUID id, @RequestBody ClientDto clientDto){
        Optional<ClientModel> clientModelOptional= clientService.findById(id);
        if(clientModelOptional.isPresent()){
            var clientModel = clientModelOptional.get();
            clientModel.setName(clientDto.getName());
            clientModel.setSurname(clientDto.getSurname());
            clientModel.setCpf(clientDto.getCpf());
            clientModel.setEmail(clientDto.getEmail());
            clientModel.setPhoneNumber(clientDto.getPhoneNumber());
            return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clientService.findAll());
    }
    @GetMapping(value="/findBy/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value="id")UUID id){
        Optional<ClientModel> clienteModelOptional = clientService.findById(id);
        return clienteModelOptional.<ResponseEntity<Object>>map(clientModel -> ResponseEntity.status(HttpStatus.OK).body(clientModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clientService.findAll()));
    }
    @GetMapping(value="/findByName/{name}/{surname}")
    public ResponseEntity<Object> getByNameAndSurname(@PathVariable(value="name")String name, @PathVariable(value="surname")String surname){
        Optional<ClientModel> clientModelOptional = clientService.findByNameAndSurname(name,surname);
        return clientModelOptional.<ResponseEntity<Object>>map(clientModel -> ResponseEntity.status(HttpStatus.OK).body(clientModel)).orElseGet(()->ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clientService.findAll()));
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id")UUID id){
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if(clientModelOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        List<BookingModel> bookings = bookingService.findAll()
                .stream()
                .filter(booking -> booking.getClient().getId().equals(clientModelOptional.get().getId()))
                .toList();
        if(!bookings.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente possui reservas!");
        }
            clientService.delete(clientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");

    }
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ClientModel>> getAllClientes(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

}
