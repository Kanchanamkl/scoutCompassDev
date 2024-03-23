package com.scoutcomapss.api.requirement;

import com.scoutcomapss.api.requirement.status.RequirementStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    public Requirement getRequirementByAwardIdAndRequirementId(Integer awardId, Integer requirementId){
        return requirementRepository.findByAwardIdAndRequirementId(awardId,requirementId).get();
    }

    public List<Requirement> getRequirementsByAwardId(Integer awardId){
        return requirementRepository.findRequirementByAwardId(awardId);
    }


}
