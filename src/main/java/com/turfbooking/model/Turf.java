package com.turfbooking.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "turfs")
public class Turf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String sports;
    private double pricePerHour;

    @ElementCollection
    private List<String> images;

    @Column(length = 1000)
    private String description;

    // Default Constructor
    public Turf() {}

    // Constructor WITHOUT ID (DataInitializer-ന് വേണ്ടിയുള്ളത്)
    public Turf(String name, String location, String sports, double pricePerHour, List<String> images, String description) {
        this.name = name;
        this.location = location;
        this.sports = sports;
        this.pricePerHour = pricePerHour;
        this.images = images;
        this.description = description;
    }

    // All-args Constructor WITH ID
    public Turf(Long id, String name, String location, String sports, double pricePerHour, List<String> images, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.sports = sports;
        this.pricePerHour = pricePerHour;
        this.images = images;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSports() { return sports; }
    public void setSports(String sports) { this.sports = sports; }

    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}