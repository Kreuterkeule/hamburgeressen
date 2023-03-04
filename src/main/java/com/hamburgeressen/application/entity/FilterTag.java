package com.hamburgeressen.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude="restaurant")
public class FilterTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;
    @ManyToMany(mappedBy = "tags")
    private List<RestaurantEntity> restaurants;

    public FilterTag(String tag) {
        this.tag = tag;
    }

    public FilterTag() {
    }

    @Override
    public String toString() {
        return "FilterTag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }
}
