package com.scoutcomapss.api.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/8/2024
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
