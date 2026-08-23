package com.turfbooking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "turfs")
public class Turf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private double price;
    
    private String openTime;
    private String closeTime;

    @Column(columnDefinition = "LONGTEXT")
    private String imageUrl;

    public Turf() {}

    public Turf(String name, String location, double price, String openTime, String closeTime, String imageUrl) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getOpenTime() { return openTime; }
    public void setOpenTime(String openTime) { this.openTime = openTime; }

    public String getCloseTime() { return closeTime; }
    public void setCloseTime(String closeTime) { this.closeTime = closeTime; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}