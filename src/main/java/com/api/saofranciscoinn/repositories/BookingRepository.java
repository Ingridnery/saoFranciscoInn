package com.api.saofranciscoinn.repositories;

import com.api.saofranciscoinn.models.booking.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingModel, UUID> {}
