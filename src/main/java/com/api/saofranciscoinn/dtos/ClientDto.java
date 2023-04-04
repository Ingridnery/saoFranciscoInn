package com.api.saofranciscoinn.dtos;

import com.api.saofranciscoinn.models.client.Cpf;
import jakarta.validation.constraints.NotBlank;

public class ClientDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Cpf
    private String cpf;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;

    public ClientDto(String name, String surname, String cpf, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
