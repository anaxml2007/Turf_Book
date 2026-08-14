package com.turfbooking.repository;

import com.turfbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    boolean existsByTurfIdAndBookingDateAndSlotTimeAndStatus(
            Long turfId, 
            LocalDate bookingDate, 
            String slotTime, 
            String status
    );
}