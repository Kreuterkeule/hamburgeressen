package com.hamburgeressen.application.repo;

import com.hamburgeressen.application.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RestaurantRepo extends JpaRepository<RestaurantEntity, Long> {

//    @Query("SELECT r FROM restaurants r WHERE r.name LIKE %:query%")
//    List<RestaurantEntity> searchRestaurantsByDescriptionAndName(@Param("query") String query);
    List<RestaurantEntity> findByNameContainingIgnoreCase(String query);

}
