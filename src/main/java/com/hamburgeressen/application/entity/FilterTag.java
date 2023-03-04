package com.hamburgeressen.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@ToString
@Getter
@Setter
@EqualsAndHashCode(exclude="restaurant")
public class FilterTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    public FilterTag(String tag) {
        this.tag = tag;
    }

    public FilterTag() {
    }
}
