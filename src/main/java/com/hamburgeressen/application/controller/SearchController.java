package com.hamburgeressen.application.controller;

import com.hamburgeressen.application.entity.FilterTag;
import com.hamburgeressen.application.entity.LocationEntity;
import com.hamburgeressen.application.entity.RestaurantEntity;
import com.hamburgeressen.application.repo.FilterTagRepo;
import com.hamburgeressen.application.repo.RestaurantRepo;
import com.hamburgeressen.application.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class SearchController {

    private RestaurantRepo restaurantRepo;

    private FilterTagRepo filterTagRepo;

    @Autowired
    public SearchController(RestaurantRepo restaurantRepo, FilterTagRepo filterTagRepo) {
        this.restaurantRepo = restaurantRepo;
        this.filterTagRepo = filterTagRepo;
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<RestaurantEntity>> getAllRestaurants() {
        System.out.println(restaurantRepo.findAll());
        return ResponseEntity.ok(restaurantRepo.findAll());
    }

    @GetMapping("createFilterTag")
    public ResponseEntity<FilterTag> createFilterTag(@RequestParam("name") String name) {

        return ResponseEntity.ok(filterTagRepo.save(new FilterTag(name)));

    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<RestaurantEntity> createRestaurant(@RequestBody RestaurantEntity restaurant){
        System.out.println(restaurant);


        RestaurantEntity toCreateRestaurant = new RestaurantEntity();

        toCreateRestaurant = restaurant;

        toCreateRestaurant = restaurantRepo.save(toCreateRestaurant);

        return ResponseEntity.ok(toCreateRestaurant);
    }

    record RequestDto(String query, LocationEntity location, Double distance, List<Long> tagIds) {}

    @CrossOrigin
    @PostMapping("/search")
    public ResponseEntity<List<RestaurantEntity>> defaultSearch(@RequestBody RequestDto requestDto) {

        List<FilterTag> tags = new ArrayList<>();

        for (Long id : requestDto.tagIds) {
            tags.add(filterTagRepo.findById(id).get());
        }

        return ResponseEntity.ok(searchService.defaultSearch(requestDto.query,
                requestDto.location.getX(),
                requestDto.location.getY(),
                requestDto.distance,
                tags));

    }
}
