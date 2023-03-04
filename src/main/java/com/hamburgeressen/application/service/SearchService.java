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

        System.out.println("Start:");
        System.out.println(restaurants);
        System.out.println(restaurantsSelected);

        for (RestaurantEntity restaurant : restaurants) {
            Double distanceX = x - restaurant.getLocation().getX();
            Double distanceY = y - restaurant.getLocation().getY();
            Double actualDistance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
            System.out.println("Distance calculation");
            System.out.println("DistanceX: " + distanceX.toString());
            System.out.println("DistanceY: " + distanceY.toString());
            System.out.println("Actual Distance: " + actualDistance.toString());
            if (actualDistance < distance) {
                restaurantsSelected.add(restaurant);
            }
        }

        System.out.println("After Distance calculation:");
        System.out.println(restaurants);
        System.out.println(restaurantsSelected);

        restaurants.clear();
        for (RestaurantEntity restaurant : restaurantsSelected) {
            restaurants.add(restaurant);
        }
        restaurantsSelected.clear();

        System.out.println("After Reset");
        System.out.println(restaurants);
        System.out.println(restaurantsSelected);

        System.out.println("Before tags");

        if (tags.isEmpty()) {
            return restaurants;
        }

        for (RestaurantEntity restaurant : restaurants) {
            System.out.println("restaurant: " + restaurant);
            System.out.println("tags: " + tags.toString());
            for (FilterTag tag : tags) {
                System.out.println("tag: " + tag);
                for(FilterTag restaurantTag : restaurant.getTags()) {
                    System.out.println("Restaurant tag: " + restaurantTag);
                }
                if (restaurant.getTags().contains(tag)) {
                    if (!restaurantsSelected.contains(restaurant)) {
                        restaurantsSelected.add(restaurant);
                    }
                }
            }
        }

        restaurants.clear();
        for (RestaurantEntity restaurant : restaurantsSelected) {
            restaurants.add(restaurant);
        }
        restaurantsSelected.clear();

        return restaurants;

    }

}
