package com.turfbooking.repository;

import com.turfbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByTurfIdAndBookingDateAndSlotTimeAndStatus(Long turfId, LocalDate bookingDate, String slotTime, String status);
}