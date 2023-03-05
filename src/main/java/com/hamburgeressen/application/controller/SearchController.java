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

    private SearchService searchService;

    @Autowired
    public SearchController(RestaurantRepo restaurantRepo, FilterTagRepo filterTagRepo, SearchService searchService) {
        this.restaurantRepo = restaurantRepo;
        this.filterTagRepo = filterTagRepo;
        this.searchService = searchService;
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

    record CreateRequestDto(String name,
                            String description,
                            String imageUrl,
                            List<Long> tagIds,
                            LocationEntity location
                            ) {}

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<RestaurantEntity> createRestaurant(@RequestBody CreateRequestDto requestDto){

        RestaurantEntity toCreateRestaurant = new RestaurantEntity();

        toCreateRestaurant.setName(requestDto.name);
        toCreateRestaurant.setDescription(requestDto.description);
        toCreateRestaurant.setImageUrl(requestDto.imageUrl);
        toCreateRestaurant.setLocation(requestDto.location);

        List<FilterTag> tags = new ArrayList<>();

        for (Long id : requestDto.tagIds) {
            tags.add(filterTagRepo.findById(id).get());
        }

        toCreateRestaurant.setTags(tags);

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

        System.out.println(requestDto.location);

        return ResponseEntity.ok(searchService.defaultSearch(requestDto.query,
                requestDto.location.getLon(),
                requestDto.location.getLat(),
                requestDto.distance,
                tags));

    }
}
