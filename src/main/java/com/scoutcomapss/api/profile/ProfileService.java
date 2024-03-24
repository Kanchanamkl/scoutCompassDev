package com.scoutcomapss.api.profile;

import com.scoutcomapss.api.auth.user.Instructor;
import com.scoutcomapss.api.auth.user.InstructorRepository;
import com.scoutcomapss.api.auth.user.Scout;
import com.scoutcomapss.api.auth.user.ScoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/3/2024
 */
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ScoutRepository scoutRepository;
    private final InstructorRepository instructorRepository;

    public ProfileResponse getScoutByEmail(String scoutEmail) {

        boolean isScoutPresent = scoutRepository.findByScoutEmail(scoutEmail).isPresent();
        if(isScoutPresent){
            Scout scout =  scoutRepository.findByScoutEmail(scoutEmail).get();
            ProfileResponse profileResponse = ProfileResponse.builder()
                    .fullName(scout.getScoutFirstName()+" "+ scout.getScoutLastname())
                    .email(scout.getScoutEmail())
                    .dob(scout.getScoutDob())
                    .district(scout.getScoutDistrict())
                    .gender(scout.getScoutGender())
                    .mobNumber(scout.getScoutMobNum())
                    .school(scout.getScoutSchool())
                    .instructor_name(scout.getInstructor().getInstructFirstName())
                    .build();

            return profileResponse;
        }else{
            return null;
        }
    }


    public ProfileResponse getInstructorByEmail(String instructorEmail) {

        boolean isInstructorPresent = instructorRepository.findInstructorByInstructEmail(instructorEmail).isPresent();
        if(isInstructorPresent){
            Instructor instructor =  instructorRepository.findInstructorByInstructEmail(instructorEmail).get();
            ProfileResponse profileResponse = ProfileResponse.builder()
                    .fullName(instructor.getInstructFirstName()+" "+ instructor.getInstructLastname())
                    .email(instructor.getInstructEmail())
                    .dob(instructor.getInstructDob())
                    .district(instructor.getInstructDistrict())
                    .gender(instructor.getInstructGender())
                    .mobNumber(instructor.getInstructMobNum())
                    .school(instructor.getInstructSchool())
                    .build();

            return profileResponse;
        }else{
            return null;
        }
    }
}
