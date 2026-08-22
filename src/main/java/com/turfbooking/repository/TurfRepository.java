package com.turfbooking.repository;

import com.turfbooking.model.Turf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurfRepository extends JpaRepository<Turf, Long> {
    List<Turf> findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(String name, String location);
}