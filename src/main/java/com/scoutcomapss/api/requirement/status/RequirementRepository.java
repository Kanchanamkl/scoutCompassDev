package com.scoutcomapss.api.requirement.status;

import com.scoutcomapss.api.requirement.status.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RequirementRepository extends JpaRepository<Requirement,Integer> {

   Optional<Requirement>  findByAwardIdAndRequirementId(Integer awardId, Integer requirementId);

}
