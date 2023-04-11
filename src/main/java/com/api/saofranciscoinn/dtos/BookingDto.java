package com.api.saofranciscoinn.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class BookingDto {
    @NotNull
    private UUID idClient;
    @NotNull
    private UUID idRoom;
    @NotNull
    private LocalDate startingDate;
    @NotNull
    private LocalDate finalDate;

    public BookingDto(UUID idClient, UUID idRoom, LocalDate startingDate, LocalDate finalDate) {
        this.idClient = idClient;
        this.idRoom = idRoom;
        this.startingDate = startingDate;
        this.finalDate = finalDate;
    }

    public UUID getIdClient() {
        return idClient;
    }

    public void setIdClient(UUID idCliente) {
        this.idClient = idCliente;
    }

    public UUID getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(UUID idRoom) {
        this.idRoom = idRoom;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate dataInicio) {
        this.startingDate = dataInicio;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate dataFim) {
        this.finalDate = dataFim;
    }

}
