package com.scoutcomapss.api.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoutRepository extends JpaRepository<Scout, Integer>  {

  Optional<Scout> findByScoutEmail(String email);



}
