package com.hamburgeressen.application.service;

import com.hamburgeressen.application.entity.FilterTag;
import com.hamburgeressen.application.entity.RestaurantEntity;
import com.hamburgeressen.application.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private RestaurantRepo restaurantRepo;

    @Autowired
    public SearchService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public List<RestaurantEntity> defaultSearch(String query, Double lon1, Double lat1, Double distance, List<FilterTag> tags) {

        List<RestaurantEntity> restaurants = new ArrayList<>();
        for (String singleWord : query.split(" ")) {
            for(RestaurantEntity restaurant : restaurantRepo.searchRestaurantsByDescriptionAndName(singleWord)) {
                if (!restaurants.contains(restaurant)) {
                    restaurants.add(restaurant);
                }
            }
        }
        List<RestaurantEntity> restaurantsSelected = new ArrayList<>();

        System.out.println("Start:");
        System.out.println(distance);
        System.out.println(restaurants);
        System.out.println(restaurantsSelected);

        System.out.println("Cords search address");
        System.out.println("lat: " + lat1);
        System.out.println("lon: " + lon1);

        lon1 = Math.toRadians(lon1);
        lat1 = Math.toRadians(lat1);

        for (RestaurantEntity restaurant : restaurants) {

            System.out.println("Cords restaurant: " + restaurant.getName());
            System.out.println("lat: " + restaurant.getLocation().getLat());
            System.out.println("lon: " + restaurant.getLocation().getLat());

            double lon2 = restaurant.getLocation().getLon();
            double lat2 = restaurant.getLocation().getLat();

            // The math module contains a function
            // named toRadians which converts from
            // degrees to radians.

            lon2 = Math.toRadians(lon2);
            lat2 = Math.toRadians(lat2);

            // Haversine formula
            double dlon = lon2 - lon1;
            double dlat = lat2 - lat1;
            double a = Math.pow(Math.sin(dlat / 2), 2)
                    + Math.cos(lat1) * Math.cos(lat2)
                    * Math.pow(Math.sin(dlon / 2),2);

            double c = 2 * Math.asin(Math.sqrt(a));

            // Radius of earth in kilometers. Use 3956
            // for miles
            double r = 6371;

            // calculate the result
            Double actualDistance = c * r;

            BigDecimal bdDistance = BigDecimal.valueOf(actualDistance);
            bdDistance.setScale(10, RoundingMode.HALF_UP);
            System.out.println(actualDistance);
            System.out.println(distance);
            System.out.println(actualDistance < distance);
            actualDistance = Math.abs(bdDistance.doubleValue());
            System.out.println("Distance calculation");
            System.out.println("Actual Distance: " + actualDistance.toString());
            if (actualDistance < distance) {
                if (!restaurantsSelected.contains(restaurant)) {
                    restaurantsSelected.add(restaurant);
                }
            }
            System.out.println(distance.getClass());
            System.out.println("Before all added to restaurants selected" + restaurantsSelected);
        }

        if (distance == 0D) {
            restaurantsSelected.addAll(restaurants);
        }

        System.out.println("After Distance calculation:");
        System.out.println(restaurants);
        System.out.println(restaurantsSelected);

        restaurants.clear();
        restaurants.addAll(restaurantsSelected);

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
        restaurants.addAll(restaurantsSelected);
        restaurantsSelected.clear();

        return restaurants;

    }

}
