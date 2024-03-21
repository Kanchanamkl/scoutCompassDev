package com.scoutcomapss.api.passing;

import com.scoutcomapss.api.auth.user.Scout;
import com.scoutcomapss.api.requirement.status.RequirementStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/scoutcompass/passing")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PassingController {

    private final PassingService passingService;

    @GetMapping("/scoutList")
    public ResponseEntity<?> getScoutListByInstructorUserName(@RequestParam String instructorEmail){

        if(passingService.getScoutListByInstructorEmail(instructorEmail)!=null){
            ArrayList<Scout> scoutList = passingService.getScoutListByInstructorEmail(instructorEmail);
            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(scoutList);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Scouts not found under the instructor!") ;
        }
    }


    @GetMapping("/requirementList")
    public ResponseEntity<?> getRequirementsByScout(@RequestParam String scoutEmail){

        if( passingService.getRequirementStatusListByScout(scoutEmail)!=null){
            List<RequirementStatus> requirementStatuses = passingService.getRequirementStatusListByScout(scoutEmail);
            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(requirementStatuses);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No Pending requirements!") ;
        }
    }




    @GetMapping
    public String testMapping(){
        return "test mapping";
    }
}
