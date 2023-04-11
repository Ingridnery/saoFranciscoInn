package com.api.saofranciscoinn.controllers;

import com.api.saofranciscoinn.dtos.BookingDto;
import com.api.saofranciscoinn.models.booking.BookingModel;
import com.api.saofranciscoinn.models.client.ClientModel;
import com.api.saofranciscoinn.models.room.RoomModel;
import com.api.saofranciscoinn.services.BookingService;
import com.api.saofranciscoinn.services.ClientService;
import com.api.saofranciscoinn.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("aluguel")
public class BookingController {
    final BookingService bookingService;
    final ClientService clientService;
    final RoomService roomService;

    public BookingController(BookingService bookingService, ClientService clientService, RoomService roomService) {
        this.bookingService = bookingService;
        this.clientService = clientService;
        this.roomService = roomService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveBooking(@Valid @RequestBody BookingDto bookingDto){
        if(!bookingService.isValidDate(bookingDto.getStartingDate(),bookingDto.getFinalDate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data final não pode ser anterior a hoje!");
        }
        Optional<RoomModel> roomModel = roomService.findById(bookingDto.getIdRoom());

        List<BookingModel> bookings = bookingService.findAll()
                .stream()
                .filter(booking -> booking.getRoom().getId().equals(bookingDto.getIdRoom()) ||
                        booking.getStartingDate().isAfter(bookingDto.getFinalDate()))
                .toList();
        if(bookings.isEmpty()){
            var bookingModel = new BookingModel();
            Optional<ClientModel> clientModel = clientService.findById(bookingDto.getIdClient());

            bookingModel.setClient(clientModel.get());
            bookingModel.setRoom(roomModel.get());

            bookingModel.setFinalDate(bookingDto.getFinalDate());
            bookingModel.setStartingDate(bookingDto.getStartingDate());
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.save(bookingModel));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esse quarto já está alugado nessa data!");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateBooking(@PathVariable UUID id, @RequestBody @Valid BookingDto bookingDto){
        Optional<BookingModel> bookingModelOptional = bookingService.findById(id);
        Optional<ClientModel> clientModel = clientService.findById(bookingDto.getIdClient());
        Optional<RoomModel> roomModel = roomService.findById(bookingDto.getIdRoom());


        List<BookingModel> bookings = bookingService.findAll()
                .stream()
                .filter(booking -> booking.getRoom().getId().equals(bookingDto.getIdRoom()) ||
                        booking.getStartingDate().isAfter(bookingDto.getFinalDate()))
                .toList();
        if(bookings.isEmpty()){
            if(bookingModelOptional.isPresent()){
                var bookingModel = bookingModelOptional.get();
                bookingModel.setRoom(roomModel.get());
                bookingModel.setClient(clientModel.get());
                bookingModel.setStartingDate(bookingDto.getStartingDate());
                bookingModel.setFinalDate(bookingDto.getFinalDate());

                return ResponseEntity.status(HttpStatus.OK).body(bookingService.save(bookingModel));
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esse quarto já está alugado nessa data!");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválido!");
    }

    @GetMapping(value = "/findBy/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id")UUID id){
        Optional<BookingModel> aluguelModelOptional = bookingService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(aluguelModelOptional.get());
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable(value = "id")UUID id){
        Optional<BookingModel> aluguelModelOptional = bookingService.findById(id);
        if(aluguelModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluguel não encontrado!");
        }
        bookingService.deleteByid(aluguelModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("Aluguel deletado com sucesso!");
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<BookingModel>> getAllBookings(){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findAll());
    }

    private static HttpStatus getBadRequest() {
        return HttpStatus.BAD_REQUEST;
    }
}
