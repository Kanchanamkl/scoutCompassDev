package com.scoutcomapss.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/5/2024
 */
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Optional<Instructor> findByInstructEmail(String email);
    Optional<Instructor> findByInstructId(Integer instructId);




}
