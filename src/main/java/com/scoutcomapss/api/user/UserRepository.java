package com.scoutcomapss.api.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Scout, Integer>  {

  Optional<Scout> findByScoutEmail(String email);

}
