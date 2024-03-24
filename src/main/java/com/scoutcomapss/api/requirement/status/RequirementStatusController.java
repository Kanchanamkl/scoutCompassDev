package com.scoutcomapss.api.requirement.status;

import com.scoutcomapss.api.auth.user.ScoutRepository;
import com.scoutcomapss.api.requirement.Requirement;
import com.scoutcomapss.api.requirement.RequirementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/scoutcompass/requirement/status")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RequirementStatusController {

    private final RequirementStatusService requirementStatusService;
    private final RequirementRepository requirementRepository;
    private final ScoutRepository scoutRepository;
    private final RequirementStatusRepository requirementStatusRepository;


    //scout registration
    @PostMapping("/marks/submit")
    public ResponseEntity<?> submitRequirementStatus(@RequestBody RequirementStatusRequest requirementStatusRequest) {
        return ResponseEntity.ok().body(requirementStatusService.createRequirementStatus(requirementStatusRequest));
    }

    @GetMapping()
    public ResponseEntity<?> getStatus( @RequestParam String userName ,  @RequestParam Integer awardId, @RequestParam Integer requirementId ) {

        System.out.println("userName-"+userName);
        System.out.println("awardId-"+awardId);
        System.out.println("requirementId-"+requirementId);
      RequirementStatus requirementStatus = requirementStatusService.getRequirementStatusByUserNameAndAwardIdAndRequirementId(userName, awardId,requirementId );
      if(requirementStatus!=null){
          Requirement requirement = requirementRepository.findByAwardIdAndRequirementId( requirementStatus.getAwardId(),requirementStatus.getRequirementId() ).get();

          RequirementStatusResponse requirementStatusResponse  = new RequirementStatusResponse();
          requirementStatusResponse.setRequirementId(requirementStatus.getRequirementId());
          requirementStatusResponse.setAwardId(requirementStatus.getAwardId());
          requirementStatusResponse.setStatus(requirementStatus.getStatus());
          requirementStatusResponse.setEnglishName(requirement.getEnglishName());
          requirementStatusResponse.setSinhalaName(requirement.getSinhalaName());
          requirementStatusResponse.setMarks(requirementStatus.getMarks());
          requirementStatusResponse.setCompletedDate(requirementStatus.getCompletedDate());
          requirementStatusResponse.setIsPracticalRequirement(requirement.getIsPracticalRequirement());
          return ResponseEntity
                  .status(HttpStatus.OK)
                  .body(requirementStatusResponse);
      }else{
          return ResponseEntity
                  .status(HttpStatus.OK)
                  .body("requirement status not found!");

      }

    }


    @GetMapping("/requirement")
    public ResponseEntity<?> getRequirementStatusByAwardIdAndRequirementId( @RequestParam Integer awardId ,  @RequestParam Integer requirementId ) {
        Requirement requirement = requirementRepository.findByAwardIdAndRequirementId( awardId,requirementId ).get();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(requirement);
    }

    @GetMapping("/requirementStatusList")
    public List<RequirementStatusResponse> getRequirementStatusListByScoutEmail(@RequestParam  String scoutEmail){
        boolean isScoutPresent =  scoutRepository.findByScoutEmail(scoutEmail).isPresent();
        ArrayList<RequirementStatusResponse> requirementStatusList = new ArrayList<>();
        if(isScoutPresent){
            List<RequirementStatus> requirementList = requirementStatusService.findRequirementStatusByUserName(scoutEmail);

//            for (RequirementStatus requirementStatus : requirementList) {
//                requirementStatusList.add(requirementStatus);
//            }

            for (int i = 0; i <requirementList.size();i++) {
              //  requirementStatusList.set(1).setSinhalaName(requirementList.get(1).requirement.getSinhalaName());
                Requirement requirement = requirementRepository.findByAwardIdAndRequirementId( requirementList.get(i).getAwardId(),requirementList.get(i).getRequirementId() ).get();
            RequirementStatusResponse requirementStatusResponse = new RequirementStatusResponse();
            requirementStatusResponse.setRequirementId(requirementList.get(i).getRequirementId());
            requirementStatusResponse.setAwardId(requirementList.get(i).getAwardId());
            requirementStatusResponse.setStatus(requirementList.get(i).getStatus());
            requirementStatusResponse.setEnglishName(requirement.getEnglishName());
            requirementStatusResponse.setSinhalaName(requirement.getSinhalaName());
            requirementStatusResponse.setMarks(requirementList.get(i).getMarks());
            requirementStatusResponse.setIsPracticalRequirement(requirement.getIsPracticalRequirement());

            if(requirement.getIsPracticalRequirement()==1&& requirementList.get(i).getStatus().equals("PENDING")){
                requirementStatusList.add(requirementStatusResponse);
            }

            }
            //  return requirementList.get(1).requirement.getSinhalaName();
            return requirementStatusList;
        }else{
            return null;
        }

    }



    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateRequirementStatus(@RequestBody RequirementStatusUpdateRequest requirementStatusUpdateRequest) {

        return ResponseEntity.ok().body(requirementStatusService.updateRequirementStatus(requirementStatusUpdateRequest));
    }

    @PostMapping("/pracical_req_status")
    public ResponseEntity<?> submitPracticalRequirementStatus(@RequestBody RequirementStatusRequest requirementStatusRequest) {
        return ResponseEntity.ok().body(requirementStatusService.createPracticalRequirementStatus(requirementStatusRequest));
    }








}
