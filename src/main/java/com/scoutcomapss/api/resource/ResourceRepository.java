package com.scoutcomapss.api.resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/19/2024
 */
public interface ResourceRepository extends JpaRepository<Resource , Long> {

    Optional<Resource> findByResourceName(String name);

    @Query("SELECT r FROM Resource r ")
    ArrayList<Resource> findAllByResourceId();

    @Query("SELECT r FROM Resource r WHERE r.resourceId = ?1")
    Resource findResourceByResourceId(Integer resourceId);

    @Query("SELECT r FROM Resource r WHERE r.resourceName = ?1")
    Resource findResourceByResourceName(String resourceName);


    @Query("SELECT COUNT(r) FROM Resource r")
    Long countAllResources();

}
