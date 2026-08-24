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
    public Turf addTurf(@RequestBody Turf turf) {
        return turfRepository.save(turf);
    }

    // UPDATE TURF (EDIT API)
    @PutMapping("/{id}")
    public ResponseEntity<Turf> updateTurf(@PathVariable Long id, @RequestBody Turf updatedTurf) {
        return turfRepository.findById(id).map(turf -> {
            turf.setName(updatedTurf.getName());
            turf.setLocation(updatedTurf.getLocation());
            turf.setPrice(updatedTurf.getPrice());
            turf.setOpenTime(updatedTurf.getOpenTime());
            turf.setCloseTime(updatedTurf.getCloseTime());
            
            // പുതിയ ഇമേജ് അയച്ചിട്ടുണ്ടെങ്കിൽ മാത്രം അപ്ഡേറ്റ് ചെയ്യും
            if (updatedTurf.getImageUrl() != null && !updatedTurf.getImageUrl().trim().isEmpty()) {
                turf.setImageUrl(updatedTurf.getImageUrl());
            }
            
            Turf savedTurf = turfRepository.save(turf);
            return ResponseEntity.ok(savedTurf);
        }).orElse(ResponseEntity.notFound().build());
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