package com.hamburgeressen.application.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations")
@Getter
@Setter
@EqualsAndHashCode(exclude = "restaurant")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Double lon;
    private Double lat;

    public LocationEntity(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public LocationEntity() {
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "id=" + id +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
