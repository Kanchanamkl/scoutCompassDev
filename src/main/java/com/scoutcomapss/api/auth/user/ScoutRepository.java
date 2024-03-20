package com.scoutcomapss.api.auth.user;

import com.scoutcomapss.api.requirement.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ScoutRepository extends JpaRepository<Scout, Integer>  {

  Optional<Scout> findByScoutEmail(String email);

  ArrayList<Scout> findScoutsByInstructor(Instructor instructor);


}
