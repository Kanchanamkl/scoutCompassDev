package com.scoutcomapss.api.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/5/2024
 */
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Optional<Instructor> findByInstructEmail(String email);
    Optional<Instructor> findByInstructId(Integer instructId);




}
