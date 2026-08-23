package com.turfbooking.service;

import com.turfbooking.model.Booking;
import com.turfbooking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        // 1. Mobile Number Validation
        if (booking.getPhoneNumber() == null || !booking.getPhoneNumber().matches("^[6-9]\\d{9}$")) {
            throw new IllegalArgumentException("Invalid 10-digit mobile number.");
        }

        // 2. Check Slot Availability
        boolean isSlotTaken = bookingRepository.existsByTurfIdAndBookingDateAndSlotTimeAndStatus(
                booking.getTurfId(), booking.getBookingDate(), booking.getSlotTime(), "CONFIRMED"
        );

        if (isSlotTaken) {
            throw new IllegalStateException("Selected slot is already booked for this turf!");
        }

        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}