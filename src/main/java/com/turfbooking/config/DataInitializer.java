package com.turfbooking.config;

import com.turfbooking.model.Turf;
import com.turfbooking.repository.TurfRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TurfRepository turfRepository;

    public DataInitializer(TurfRepository turfRepository) {
        this.turfRepository = turfRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (turfRepository.count() == 0) {
            turfRepository.save(new Turf("Green Arena", "Thrissur", 1200.0, "05:00", "23:00", "/images/turf1.jpg"));
            turfRepository.save(new Turf("Kickoff Turf", "Kochi", 1500.0, "06:00", "00:00", "/images/turf2.jpg"));
        }
    }
}