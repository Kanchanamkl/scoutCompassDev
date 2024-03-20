package com.scoutcomapss.api.passing;

import com.scoutcomapss.api.auth.user.Instructor;
import com.scoutcomapss.api.auth.user.InstructorRepository;
import com.scoutcomapss.api.auth.user.Scout;
import com.scoutcomapss.api.auth.user.ScoutRepository;
import com.scoutcomapss.api.event.Event;
import com.scoutcomapss.api.profile.ProfileResponse;
import com.scoutcomapss.api.requirement.status.RequirementStatus;
import com.scoutcomapss.api.requirement.status.RequirementStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PassingService {
    private final ScoutRepository scoutRepository;
    private final InstructorRepository instructorRepository;
    private final RequirementStatusRepository requirementStatusRepository;

    public ArrayList<Scout> getScoutListByInstructorEmail(String instructorEmail){
        boolean isInstructorPresent =  instructorRepository.findInstructorByInstructEmail(instructorEmail).isPresent();
        if(isInstructorPresent){
            Instructor instructor =  instructorRepository.findInstructorByInstructEmail(instructorEmail).get();
            ArrayList<Scout> scoutList = scoutRepository.findScoutsByInstructor(instructor);
            return  scoutList;
        }else{
            return null;
        }

    }


    public ArrayList<RequirementStatus> getRequirementStatusListByScout(String scoutEmail){
        boolean isScoutPresent =  scoutRepository.findByScoutEmail(scoutEmail).isPresent();
        if(isScoutPresent){
            ArrayList<RequirementStatus> requirementList = requirementStatusRepository.findRequirementStatusByUserName(scoutEmail);
            return  requirementList;
        }else{
            return null;
        }

    }









}
