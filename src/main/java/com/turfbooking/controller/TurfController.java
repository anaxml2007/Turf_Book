package com.turfbooking.controller;

import com.turfbooking.model.Turf;
import com.turfbooking.repository.TurfRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turfs")
@CrossOrigin(origins = "*")
public class TurfController {

    private final TurfRepository turfRepository;

    public TurfController(TurfRepository turfRepository) {
        this.turfRepository = turfRepository;
    }

    @GetMapping
    public List<Turf> getAllTurfs() {
        return turfRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Turf> addTurf(@RequestBody Turf turf) {
        if (turf.getImageUrl() == null || turf.getImageUrl().trim().isEmpty()) {
            turf.setImageUrl("/images/turf1.jpg");
        }
        Turf savedTurf = turfRepository.save(turf);
        return ResponseEntity.ok(savedTurf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turf> updateTurf(@PathVariable Long id, @RequestBody Turf turfDetails) {
        return turfRepository.findById(id)
                .map(turf -> {
                    turf.setName(turfDetails.getName());
                    turf.setLocation(turfDetails.getLocation());
                    turf.setPrice(turfDetails.getPrice());
                    turf.setAvailableSlots(turfDetails.getAvailableSlots());
                    if (turfDetails.getImageUrl() != null && !turfDetails.getImageUrl().trim().isEmpty()) {
                        turf.setImageUrl(turfDetails.getImageUrl());
                    }
                    Turf updatedTurf = turfRepository.save(turf);
                    return ResponseEntity.ok(updatedTurf);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurf(@PathVariable Long id) {
        if (turfRepository.existsById(id)) {
            turfRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}