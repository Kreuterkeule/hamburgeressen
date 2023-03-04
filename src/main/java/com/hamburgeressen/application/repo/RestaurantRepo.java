package com.hamburgeressen.application.repo;

import com.hamburgeressen.application.entity.LocationEntity;
import com.hamburgeressen.application.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RestaurantRepo extends JpaRepository<RestaurantEntity, Long> {
    @Query("select r from RestaurantEntity r where r.name like %:query% " +
            "or r.description like %:query%")
    List<RestaurantEntity> searchRestaurantsByDescriptionAndName(@Param("query") String query);

    @Query("select r from RestaurantEntity r where r.name like %:query% " +
            "or r.description like %:query%")
    List<RestaurantEntity> defaultSearch(@Param("query") String query//,
//                                         @Param("x") Double x,
//                                         @Param("y") Double y,
//                                         @Param("distance") Integer distance
    );

}
