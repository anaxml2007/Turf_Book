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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurf(@PathVariable Long id) {
        if (turfRepository.existsById(id)) {
            turfRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}