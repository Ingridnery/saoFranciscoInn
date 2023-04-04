package com.api.saofranciscoinn.dtos;

import com.api.saofranciscoinn.models.room.BedType;
import com.api.saofranciscoinn.models.room.RoomType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomDto {

    @NotNull
    private int dailyValue;
    @NotNull
    private BedType bedType;
    @NotNull
    private RoomType roomType;

    public RoomDto(int dailyValue, BedType bedType, RoomType roomType) {
        this.dailyValue = dailyValue;
        this.bedType = bedType;
        this.roomType = roomType;
    }

    public int getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(int dailyValue) {
        this.dailyValue = dailyValue;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
