package com.turfbooking.service;

import com.turfbooking.model.Booking;
import com.turfbooking.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        // 1. Validations
        LocalDate today = LocalDate.now();
        LocalDate maxAllowedDate = today.plusYears(1);

        if (booking.getBookingDate() == null || booking.getBookingDate().isBefore(today) || booking.getBookingDate().isAfter(maxAllowedDate)) {
            throw new IllegalArgumentException("Booking date must be valid and within 1 year.");
        }

        // Null Safe Age Check
        if (booking.getAge() == null || booking.getAge() <= 0 || booking.getAge() >= 100) {
            throw new IllegalArgumentException("Age must be between 1 and 99.");
        }

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

        // 3. Price Calculation (Safe Duration Handling)
        String slotTime = booking.getSlotTime();
        double ratePerHour = (slotTime != null && (slotTime.contains("Night") || slotTime.contains("07:00 PM"))) ? 750.0 : 500.0;
        
        // Duration null ആണെങ്കിൽ default ആയി 1 മണിക്കൂർ കണക്കാക്കും
        int duration = (booking.getDurationHours() != null && booking.getDurationHours() > 0) ? booking.getDurationHours() : 1;
        
        booking.setDurationHours(duration);
        booking.setTotalPrice(ratePerHour * duration);
        booking.setStatus("CONFIRMED");

        // 4. Save to Database
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}