package com.hamburgeressen.application.service;

import com.hamburgeressen.application.entity.FilterTag;
import com.hamburgeressen.application.entity.RestaurantEntity;
import com.hamburgeressen.application.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private RestaurantRepo restaurantRepo;

    @Autowired
    public SearchService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public List<RestaurantEntity> defaultSearch(String query, Double x, Double y, Double distance, List<FilterTag> tags) {

        List<RestaurantEntity> restaurants = restaurantRepo.searchRestaurantsByDescriptionAndName(query);

        List<RestaurantEntity> restaurantsSelected = new ArrayList<>();

        for (RestaurantEntity restaurant : restaurants) {
            Double distanceX = x - restaurant.getLocation().getX();
            Double distanceY = y - restaurant.getLocation().getY();
            Double actualDistance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
            if (actualDistance < distance) {
                restaurantsSelected.add(restaurant);
            }
        }

        restaurants = restaurantsSelected;
        restaurantsSelected.clear();

        for (RestaurantEntity restaurant : restaurants) {
            for (FilterTag tag : tags) {
                if (restaurant.getTags().contains(tag)) {
                    if (!restaurantsSelected.contains(restaurant)) {
                        restaurantsSelected.add(restaurant);
                    }
                }
            }
        }

        restaurants = restaurantsSelected;
        restaurantsSelected.clear();

        return restaurants;

    }

}
