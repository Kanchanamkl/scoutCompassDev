package com.scoutcomapss.api.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/5/2024
 */
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Optional<Instructor> findByInstructId(Integer instructId);
    Optional<Instructor> findInstructorByInstructEmail(String instructEmail);


    @Query("SELECT COUNT(i) FROM Instructor i")
    Long countAllInstructor();


}
