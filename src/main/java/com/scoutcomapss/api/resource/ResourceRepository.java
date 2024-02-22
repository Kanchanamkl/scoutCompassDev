package com.scoutcomapss.api.resource;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/19/2024
 */
public interface ResourceRepository extends JpaRepository<Resource , Long> {

    Optional<Resource> findByResourceName(String name);
}
