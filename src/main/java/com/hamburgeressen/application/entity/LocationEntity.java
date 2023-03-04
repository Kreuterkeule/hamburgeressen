package com.hamburgeressen.application.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations")
@Data
@Getter
@Setter
@ToString
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Double x;
    private Double y;

    public LocationEntity(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public LocationEntity() {
    }
}