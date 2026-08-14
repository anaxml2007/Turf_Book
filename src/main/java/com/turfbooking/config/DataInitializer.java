package com.turfbooking.config;

import com.turfbooking.model.Turf;
import com.turfbooking.repository.TurfRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(TurfRepository turfRepository) {
        return args -> {
            if (turfRepository.count() == 0) {
                Turf turf1 = new Turf(
                        "Metro Soccer Turf",
                        "Thrissur",
                        "Round West, Thrissur",
                        4.8,
                        Arrays.asList("5-a-side", "Floodlights", "Parking"),
                        "https://example.com/turf1.jpg"
                );

                Turf turf2 = new Turf(
                        "Kalyan Sports Arena",
                        "Thrissur",
                        "Kodungallur Road, Thrissur",
                        4.6,
                        Arrays.asList("7-a-side", "Cafeteria"),
                        "https://example.com/turf2.jpg"
                );

                Turf turf3 = new Turf(
                        "Kochi Turf Park",
                        "Kochi",
                        "Edappally, Kochi",
                        4.9,
                        Arrays.asList("5-a-side", "A/C Locker Room"),
                        "https://example.com/turf3.jpg"
                );

                turfRepository.saveAll(Arrays.asList(turf1, turf2, turf3));
                System.out.println("Sample turf data initialized!");
            }
        };
    }
}