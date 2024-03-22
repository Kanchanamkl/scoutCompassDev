package com.scoutcomapss.api.requirement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


public interface RequirementStatusRepository extends JpaRepository<RequirementStatus, Long> {

    RequirementStatus findRequirementStatusByUserNameAndAwardIdAndRequirementId(String userName,Integer awardId, Integer requirementId);

    ArrayList<RequirementStatus> findRequirementStatusByUserName(String ScoutEmail);

    @Modifying
    @Transactional
    @Query("UPDATE RequirementStatus rs SET rs.marks = :marks WHERE rs.id = :id")
    void updateMarksById(Long id, Integer marks);




}
