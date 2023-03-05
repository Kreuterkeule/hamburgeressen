package com.hamburgeressen.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter @Setter
@EqualsAndHashCode(exclude="location,tags")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_filter_tags", joinColumns = @JoinColumn(name = "restaurant_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "filter_tag_id", referencedColumnName = "id"))
    private List<FilterTag> tags;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;

    public RestaurantEntity(String name, String description, String imageUrl, List<FilterTag> tags, LocationEntity location) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.location = location;
    }

    public RestaurantEntity() {
    }

    @Override
    public String toString() {
        return "RestaurantEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", tags=" + tags +
                ", location=" + location +
                '}';
    }
}