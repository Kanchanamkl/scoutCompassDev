package com.scoutcomapss.api.profile;

import com.scoutcomapss.api.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/3/2024
 */

@RestController
@RequestMapping("/api/scoutcompass/profile")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/scout/{scoutEmail}")
    public ResponseEntity<?> getScoutByEmail(@PathVariable String scoutEmail){

    if(profileService.getScoutByEmail(scoutEmail)!=null){
        return ResponseEntity.ok().body(profileService.getScoutByEmail(scoutEmail));
    }else{
      return ResponseEntity.badRequest().body("Scout not found!") ;
    }
    }


    @GetMapping("/instructor/{instructorEmail}")
    public ResponseEntity<?> getInstructorByEmail(@PathVariable String instructorEmail){

        if(profileService.getInstructorByEmail(instructorEmail)!=null){
            return ResponseEntity.ok().body(profileService.getInstructorByEmail(instructorEmail));
        }else{
            return ResponseEntity.badRequest().body("instructor not found!") ;
        }
    }



}
