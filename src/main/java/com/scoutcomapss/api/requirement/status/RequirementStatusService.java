package com.scoutcomapss.api.requirement.status;

import com.scoutcomapss.api.requirement.Requirement;
import com.scoutcomapss.api.requirement.RequirementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class RequirementStatusService {

    @Autowired
    private RequirementStatusRepository requirementStatusRepository;
    @Autowired
    private RequirementRepository requirementRepository;

    public String createRequirementStatus(RequirementStatusRequest  requirementStatusRequest){

        Optional<Requirement> requirement = requirementRepository.findByAwardIdAndRequirementId(requirementStatusRequest.getAwardId(),requirementStatusRequest.getRequirementId());

        RequirementStatus  requirementStatus = RequirementStatus.builder()
                .userName(requirementStatusRequest.getUserName())
                .awardId(requirementStatusRequest.getAwardId())
                .requirementId(requirementStatusRequest.getRequirementId())
                .marks(requirementStatusRequest.getMarks())
                .status(getRequirementStatus(requirementStatusRequest.getMarks()))
                .completedDate(LocalDate.now())
                .requirement(requirement.get())
                .build();

        RequirementStatus requirementStatus_ =  requirementStatusRepository.findRequirementStatusByUserNameAndAwardIdAndRequirementId(requirementStatusRequest.getUserName(),requirementStatusRequest.getAwardId(),requirementStatusRequest.getRequirementId());
        if(requirementStatus_!=null){
            requirementStatusRepository.updateMarksAndStatusById(requirementStatus_.getId(),requirementStatusRequest.getMarks(),getRequirementStatus(requirementStatusRequest.getMarks()));
        }else{
            requirementStatusRepository.save(requirementStatus);
        }


        return "Requirement status inserted successfully ! ";
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

    public List<RequirementStatus> findRequirementStatusByUserName(String scoutEmail){
        List<RequirementStatus> questionArrayList =  requirementStatusRepository.findRequirementStatusByUserName(scoutEmail);
        return questionArrayList;
    }


    public String updateRequirementStatus(RequirementStatusUpdateRequest  requirementStatusUpdateRequest){

        RequirementStatus requirementStatus_ =  requirementStatusRepository.findRequirementStatusByUserNameAndAwardIdAndRequirementId(requirementStatusUpdateRequest.getUserName(),requirementStatusUpdateRequest.getAwardId(),requirementStatusUpdateRequest.getRequirementId());

        requirementStatusRepository.updateStatusById(requirementStatus_.getId(),requirementStatusUpdateRequest.getNewStatus());

        return "Requirement status updated : "+requirementStatus_.getStatus()+" to " +requirementStatusUpdateRequest.getNewStatus();

    }

    public String createPracticalRequirementStatus(RequirementStatusRequest  requirementStatusRequest){

        Optional<Requirement> requirement = requirementRepository.findByAwardIdAndRequirementId(requirementStatusRequest.getAwardId(),requirementStatusRequest.getRequirementId());

        RequirementStatus  requirementStatus = RequirementStatus.builder()
                .userName(requirementStatusRequest.getUserName())
                .awardId(requirementStatusRequest.getAwardId())
                .requirementId(requirementStatusRequest.getRequirementId())
                .marks(0)
                .status(requirementStatusRequest.getStatus())
                .completedDate(LocalDate.now())
                .requirement(requirement.get())
                .build();

 //       RequirementStatus requirementStatus_ =  requirementStatusRepository.findRequirementStatusByUserNameAndAwardIdAndRequirementId(requirementStatusRequest.getUserName(),requirementStatusRequest.getAwardId(),requirementStatusRequest.getRequirementId());
//        if(requirementStatus_!=null){
//            requirementStatusRepository.updateMarksAndStatusById(requirementStatus_.getId(),requirementStatusRequest.getMarks(),getRequirementStatus(requirementStatusRequest.getMarks()));
//        }else{
            requirementStatusRepository.save(requirementStatus);
//        }


        return "Practical Requirement status inserted successfully ! ";
    }


}
