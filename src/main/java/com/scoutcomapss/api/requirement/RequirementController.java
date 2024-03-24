package com.scoutcomapss.api.requirement;

import com.scoutcomapss.api.requirement.status.RequirementStatus;
import com.scoutcomapss.api.requirement.status.RequirementStatusRequest;
import com.scoutcomapss.api.requirement.status.RequirementStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scoutcompass/requirement")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RequirementController {
    private final RequirementService requirementService;

    @GetMapping("/requirements_by_awards")
    public ResponseEntity<?> getRequirementsByAwardId(@RequestParam Integer awardId) {
        List<Requirement> requirementList = requirementService.getRequirementsByAwardId(awardId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(requirementList);
    }


}
