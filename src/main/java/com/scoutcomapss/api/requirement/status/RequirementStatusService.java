package com.scoutcomapss.api.requirement.status;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/14/2024
 */

@Service
@RequiredArgsConstructor
public class RequirementStatusService {

    @Autowired
    private RequirementStatusRepository requirementStatusRepository;

    public String createRequirementService(RequirementStatusRequest  requirementStatusRequest){


        RequirementStatus  requirementStatus = RequirementStatus.builder()
                .userName(requirementStatusRequest.getUserName())
                .awardId(requirementStatusRequest.getAwardId())
                .requirementId(requirementStatusRequest.getRequirementId())
                .marks(requirementStatusRequest.getMarks())
                .status(getRequirementStatus(requirementStatusRequest.getMarks()))
                .build();

        RequirementStatus requirementStatus_ =  requirementStatusRepository.findRequirementStatusByUserNameAndAwardIdAndRequirementId(requirementStatusRequest.getUserName(),requirementStatusRequest.getAwardId(),requirementStatusRequest.getRequirementId());
        if(requirementStatus_!=null){
            requirementStatusRepository.delete(requirementStatus_);
        }

        requirementStatusRepository.save(requirementStatus);

        if (requirementStatus != null) {
            return "Requirement status inserted successfully ! ";
        }
        return null;
    }

    public RequirementStatus getRequirementStatusByUserNameAndAwardIdAndRequirementId(String userName, Integer awardId , Integer requirementId) {
        RequirementStatus requirementStatus = requirementStatusRepository.findRequirementStatusByUserNameAndAwardIdAndRequirementId(userName,awardId,requirementId);
        return requirementStatus;
    }

    private String getRequirementStatus(Integer marks){
        if(marks != null){
            if(marks>=70){
                return "COMPLETED";
            }else{
                return "RE-ATTEMPT";
            }
        }else{
            return "ATTEMPT";
        }

    }
}
