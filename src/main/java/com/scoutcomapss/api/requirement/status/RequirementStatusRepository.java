package com.scoutcomapss.api.requirement.status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface RequirementStatusRepository extends JpaRepository<RequirementStatus, Long> {

    RequirementStatus findRequirementStatusByUserNameAndAwardIdAndRequirementId(String userName,Integer awardId, Integer requirementId);

    ArrayList<RequirementStatus> findRequirementStatusByUserName(String ScoutEmail);




}
