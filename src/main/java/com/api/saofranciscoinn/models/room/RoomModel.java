package com.api.saofranciscoinn.models.room;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "room")
public class RoomModel implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false, length = 10)
    private int dailyValue;
    @Column(nullable = false)
    private BedType bedType;
    @Column(nullable = false)
    private RoomType roomType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
