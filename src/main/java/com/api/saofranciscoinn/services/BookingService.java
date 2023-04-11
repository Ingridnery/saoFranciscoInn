package com.api.saofranciscoinn.services;

import com.api.saofranciscoinn.models.booking.BookingModel;
import com.api.saofranciscoinn.repositories.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {
    final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public BookingModel save(BookingModel bookingModel){
        return bookingRepository.save(bookingModel);
    }

    public List<BookingModel> findAll(){
        return bookingRepository.findAll();
    }

    public Optional<BookingModel> findById(UUID id) {
        return bookingRepository.findById(id);
    }

    @Transactional
    public void deleteByid(UUID id){
        bookingRepository.deleteById(id);}

    public boolean isValidDate(LocalDate startingDate, LocalDate finalDate) {
        return !startingDate.isBefore(LocalDate.now()) && !finalDate.isBefore(startingDate);
    }
}
