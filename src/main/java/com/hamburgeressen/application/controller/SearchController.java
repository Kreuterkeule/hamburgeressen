package com.hamburgeressen.application.controller;

import com.hamburgeressen.application.entity.FilterTag;
import com.hamburgeressen.application.entity.LocationEntity;
import com.hamburgeressen.application.entity.RestaurantEntity;
import com.hamburgeressen.application.repo.FilterTagRepo;
import com.hamburgeressen.application.repo.RestaurantRepo;
import com.hamburgeressen.application.service.LocationService;
import com.hamburgeressen.application.service.SearchService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class SearchController {

    private RestaurantRepo restaurantRepo;

    private FilterTagRepo filterTagRepo;

    private SearchService searchService;

    private JwtDecoder jwtDecoder;

    private LocationService locationService;

    @Autowired
    public SearchController(RestaurantRepo restaurantRepo, FilterTagRepo filterTagRepo, SearchService searchService, JwtDecoder jwtDecoder, LocationService locationService) {
        this.restaurantRepo = restaurantRepo;
        this.filterTagRepo = filterTagRepo;
        this.searchService = searchService;
        this.jwtDecoder = jwtDecoder;
        this.locationService = locationService;
    }

    @CrossOrigin
    @GetMapping("/allTags")
    public ResponseEntity<List<FilterTag>> getAllFilterTags() {
        System.out.println(filterTagRepo.findAll());
        return ResponseEntity.ok(filterTagRepo.findAll());
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<RestaurantEntity>> getAllRestaurants() {
        System.out.println(restaurantRepo.findAll());
        return ResponseEntity.ok(restaurantRepo.findAll());
    }

    @CrossOrigin
    @GetMapping("createFilterTag")
    public ResponseEntity<FilterTag> createFilterTag(@RequestParam("name") String name) {

        return ResponseEntity.ok(filterTagRepo.save(new FilterTag(name)));

    }

    record CreateRequestDto(String name,
                            String description,
                            String imageUrl,
                            List<Long> tagIds,
                            LocationEntity location,
                            String street,
                            String city,
                            String postalCode,
                            String country,
                            String phoneNumber,
                            String houseNumber
                            ) {}

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<RestaurantEntity> createRestaurant(@RequestBody CreateRequestDto requestDto, HttpServletRequest request){

        String jwtToken = "";
        try {
            jwtToken = request.getHeader("Authorization").substring(7);
        } catch(Exception e) {

            List<FilterTag> filterTagsNotAuthenticated = new ArrayList<>();
            filterTagsNotAuthenticated.add(new FilterTag("not authenticated"));

            return ResponseEntity.ok(new RestaurantEntity(
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    filterTagsNotAuthenticated,
                    new LocationEntity(0D,0D)
                    ));
        }

        if (jwtDecoder.decode(jwtToken).getExpiresAt().isAfter(Instant.now()) && jwtDecoder.decode(jwtToken).getClaims().get("role").equals("ADMIN")) {

            RestaurantEntity toCreateRestaurant = new RestaurantEntity();

            toCreateRestaurant.setName(requestDto.name);
            toCreateRestaurant.setDescription(requestDto.description);
            toCreateRestaurant.setImageUrl(requestDto.imageUrl);
            toCreateRestaurant.setLocation(requestDto.location);
            toCreateRestaurant.setCity(requestDto.city);
            toCreateRestaurant.setCountry(requestDto.country);
            toCreateRestaurant.setStreet(requestDto.street);
            toCreateRestaurant.setHouseNumber(requestDto.houseNumber);
            toCreateRestaurant.setPhoneNumber(requestDto.phoneNumber);
            toCreateRestaurant.setPostalCode(requestDto.postalCode);

            List<FilterTag> tags = new ArrayList<>();

            for (Long id : requestDto.tagIds) {
                tags.add(filterTagRepo.findById(id).get());
            }

            toCreateRestaurant.setTags(tags);

            toCreateRestaurant = restaurantRepo.save(toCreateRestaurant);

            return ResponseEntity.ok(toCreateRestaurant);

        } else {

            System.out.println("jwt invalid");

            List<FilterTag> filterTagsNotAuthenticated = new ArrayList<>();
            filterTagsNotAuthenticated.add(new FilterTag("not authenticated"));

            return ResponseEntity.ok(new RestaurantEntity(
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    "not authenticated",
                    filterTagsNotAuthenticated,
                    new LocationEntity(0D,0D)
            ));
        }

    }

    record RequestDto(String query, LocationEntity location, Double distance, List<Long> tagIds) {}

    @CrossOrigin
    @PostMapping("/search")
    public ResponseEntity<List<RestaurantEntity>> defaultSearch(@RequestBody RequestDto requestDto) {

        List<FilterTag> tags = new ArrayList<>();

        for (Long id : requestDto.tagIds) {
            FilterTag tag = filterTagRepo.findById(id).orElse(null);
            if (tag != null) {
                tags.add(tag);
            }
        }

        System.out.println(requestDto.location);

        return ResponseEntity.ok(searchService.defaultSearch(requestDto.query,
                requestDto.location.getLon(),
                requestDto.location.getLat(),
                requestDto.distance,
                tags));

    }

    @CrossOrigin
    @GetMapping("/deleteRestaurantById")
    public ResponseEntity<String> deleteRestaurant(@RequestParam("id") Long id, HttpServletRequest request) {
        String jwtToken = "";
        System.out.println("Deleting restaurant with id: " + id);
        RestaurantEntity toDeleteRestaurant = restaurantRepo.getById(id);
        try {
            jwtToken = request.getHeader("Authorization").substring(7);
        } catch(Exception e) {
            return ResponseEntity.ok("not succeeded");
        }
        if (jwtDecoder.decode(jwtToken).getExpiresAt().isAfter(Instant.now()) && jwtDecoder.decode(jwtToken).getClaims().get("role").equals("ADMIN")){
            System.out.println("Bearer: " + jwtToken);
            System.out.println("authenticated successful");
            List<FilterTag> tags = new ArrayList<>();
            tags.addAll(toDeleteRestaurant.getTags());
            for (FilterTag tag : tags) {
                toDeleteRestaurant.getTags().remove(tag);
            }
            restaurantRepo.deleteById(id);
        }
        System.out.println("Bearer: " + jwtToken);
        return ResponseEntity.ok("success");
    }

    record DeleteTagsDto(List<Long> tagIds) {}

    @CrossOrigin
    @PostMapping("/deleteTagsById")
    public ResponseEntity<String> deleteTag(@RequestBody DeleteTagsDto deleteTagsDto, HttpServletRequest request) {
        String jwtToken = "";

        try {
            jwtToken = request.getHeader("Authorization").substring(7);
        } catch(Exception e) {
            return ResponseEntity.ok("not succeeded");
        }
        if (jwtDecoder.decode(jwtToken).getExpiresAt().isAfter(Instant.now()) && jwtDecoder.decode(jwtToken).getClaims().get("role").equals("ADMIN")){
            filterTagRepo.deleteAllById(deleteTagsDto.tagIds);
        }
        return ResponseEntity.ok("success");
    }

    @CrossOrigin
    @GetMapping("/getRestaurantById")
    public ResponseEntity<RestaurantEntity> getRById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(restaurantRepo.findById(id).orElse(null));
    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity<RestaurantEntity> updateRestaurant(@RequestParam("id") Long id, @RequestBody CreateRequestDto createRequestDto, HttpServletRequest request) {
        String jwtToken = "";
        try {
            jwtToken = request.getHeader("Authorization").substring(7);
        } catch(Exception e) {
            return ResponseEntity.ok(new RestaurantEntity());
        }
        if (jwtDecoder.decode(jwtToken).getExpiresAt().isAfter(Instant.now()) && jwtDecoder.decode(jwtToken).getClaims().get("role").equals("ADMIN")) {

            List<FilterTag> tags = new ArrayList<>();
            tags.addAll(filterTagRepo.findAllById(createRequestDto.tagIds()));
            RestaurantEntity toCreateRestaurant = new RestaurantEntity(
                    createRequestDto.name,
                    createRequestDto.description,
                    createRequestDto.imageUrl,
                    createRequestDto.street,
                    createRequestDto.houseNumber,
                    createRequestDto.postalCode,
                    createRequestDto.city,
                    createRequestDto.country,
                    createRequestDto.phoneNumber,
                    tags,
                    createRequestDto.location
            );

            toCreateRestaurant.setId(id);

            toCreateRestaurant = restaurantRepo.save(toCreateRestaurant);

            return ResponseEntity.ok(toCreateRestaurant);
        }
        return ResponseEntity.ok(new RestaurantEntity());
    }

    @CrossOrigin
    @PostMapping("/createTag")
    public ResponseEntity<FilterTag> createRestaurant(@RequestBody FilterTag tag, HttpServletRequest request){
        String jwtToken = "";
        try {
            jwtToken = request.getHeader("Authorization").substring(7);
        } catch(Exception e) {
            return ResponseEntity.ok(new FilterTag("no token present"));
        }
        try {
            if (jwtDecoder.decode(jwtToken).getExpiresAt().isAfter(Instant.now()) && jwtDecoder.decode(jwtToken).getClaims().get("role").equals("ADMIN")) {

                FilterTag toCreateFilterTag;

                toCreateFilterTag = tag;

                System.out.println(tag);

                toCreateFilterTag = filterTagRepo.save(toCreateFilterTag);

                return ResponseEntity.ok(toCreateFilterTag);
            }
        } catch (Exception e) {
            System.out.println("could not decode token");
            return ResponseEntity.ok(new FilterTag("Not Authenticated"));
        }

        return ResponseEntity.ok(new FilterTag("Not Authenticated"));
    }

    @CrossOrigin
    @GetMapping("getLatLon")
    public ResponseEntity<LocationEntity> getLongLat(@RequestParam("address") String address) throws IOException {

        if (address.equals("")) {
            return ResponseEntity.ok(new LocationEntity(0D,0D));
        }
        LocationEntity location = locationService.getLocationForAddress(address);

        return ResponseEntity.ok(location);

    }
}