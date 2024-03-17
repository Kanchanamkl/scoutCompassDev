package com.scoutcomapss.api.requirement.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/scoutcompass/requirement/status")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RequirementStatusController {

    private final RequirementStatusService requirementStatusService;


    //scout registration
    @PostMapping("/marks/submit")
    public ResponseEntity<?> submitRequirement(@RequestBody RequirementStatusRequest requirementStatusRequest) {
        return ResponseEntity.ok().body(requirementStatusService.createRequirementService(requirementStatusRequest));
    }

    @GetMapping()
    public ResponseEntity<?> getStatus( @RequestParam String userName ,  @RequestParam Integer awardId, @RequestParam Integer requirementId ) {
      RequirementStatus requirementStatus = requirementStatusService.getRequirementStatusByUserNameAndAwardIdAndRequirementId(userName, awardId,requirementId );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(requirementStatus);
    }





}
