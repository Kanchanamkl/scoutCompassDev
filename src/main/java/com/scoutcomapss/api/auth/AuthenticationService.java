package com.scoutcomapss.api.auth;


import com.scoutcomapss.api.config.JwtService;
import com.scoutcomapss.api.auth.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final InstructorRepository instructorRepository;
    private final ScoutRepository scoutRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponse registerScout(ScoutRegisterRequest scoutRegisterRequest) {
        boolean isScoutPresent = scoutRepository.findByScoutEmail(scoutRegisterRequest.getScoutEmail()).isPresent();
        if(isScoutPresent){
            return  null;
        }else{
            Optional<Instructor> instructor = instructorRepository.findByInstructId(Integer.valueOf(scoutRegisterRequest.getInstructorId()));

            Scout scout = Scout.builder()
                    .enabled(true)   // this boolean should be false after implementation of account activation.
                    .scoutFirstName(scoutRegisterRequest.getScoutFirstName())
                    .scoutLastname(scoutRegisterRequest.getScoutLastname())
                    .scoutEmail(scoutRegisterRequest.getScoutEmail())
                    .scoutDob(scoutRegisterRequest.getScoutDob())
                    .scoutGender(scoutRegisterRequest.getScoutGender())
                    .scoutMobNum(scoutRegisterRequest.getScoutMobNum())
                    .scoutSchool(scoutRegisterRequest.getScoutSchool())
                    .scoutDistrict(scoutRegisterRequest.getScoutDistrict())
                    .scoutPassword(passwordEncoder.encode(scoutRegisterRequest.getScoutPassword()))
                    .instructor(instructor.get())
                    .role(Role.ROLE_SCOUT)
                    .build();

            scoutRepository.save(scout);

            User user = User.builder()
                    .userName(scoutRegisterRequest.getScoutEmail())
                    .userPassword(passwordEncoder.encode(scoutRegisterRequest.getScoutPassword()))
                    .role(Role.ROLE_SCOUT)
                    .build();

            userRepository.save(user);


            // todo: email verification and account activation part - sachini


            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }

    }


    public AuthenticationResponse registerInstruct(InstructRegisterRequest instructRegisterRequest) {
        Boolean isInstructorPresent = instructorRepository.findInstructorByInstructEmail(instructRegisterRequest.getInstructEmail()).isPresent();
        if(isInstructorPresent){
            return  null;
        }else{

            Instructor instructor = Instructor.builder()
                    .enabled(true)   // this boolean should be false after implementation of account activation.
                    .instructFirstName(instructRegisterRequest.getInstructFirstName())
                    .instructLastname(instructRegisterRequest.getInstructLastname())
                    .instructEmail(instructRegisterRequest.getInstructEmail())
                    .instructDob(instructRegisterRequest.getInstructDob())
                    .instructGender(instructRegisterRequest.getInstructGender())
                    .instructMobNum(instructRegisterRequest.getInstructMobNum())
                    .instructSchool(instructRegisterRequest.getInstructSchool())
                    .instructPassword(passwordEncoder.encode(instructRegisterRequest.getInstructPassword()))
                    .instructWarrantId(instructRegisterRequest.getInstructWarrantId())
                    .instructDistrict(instructRegisterRequest.getInstructDistrict())
                    .role(Role.ROLE_INSTRUCTOR)
                    .build();

            instructorRepository.save(instructor);

            User user = User.builder()
                    .userName(instructRegisterRequest.getInstructEmail())
                    .userPassword(passwordEncoder.encode(instructRegisterRequest.getInstructPassword()))
                    .role(Role.ROLE_INSTRUCTOR)
                    .build();

            userRepository.save(user);

            // todo: email verification and account activation part - sachini


            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }

    }


    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUserName(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }



    public List<Instructor> getInstructorList(){
        List<Instructor> instructorList = instructorRepository.findAll();
        return  instructorList;
    }

    public User getUserByUserEmail(String userEmail){
        User user =userRepository.findByUserName(userEmail).get();
        return user;
    }

    public AuthenticationResponse registerAdmin(AdminRegisterRequest adminRegisterRequest ){
        boolean isUserPresent = userRepository.findByUserName(adminRegisterRequest.getAdminEmail()).isPresent();
        if(isUserPresent){
            return null;
        }else{
            User user = User.builder()
                    .userName(adminRegisterRequest.getAdminEmail())
                    .userPassword(passwordEncoder.encode(adminRegisterRequest.getAdminPassword()))
                    .role(Role.ROLE_ADMIN)
                    .build();

            userRepository.save(user);

            // todo: email verification and account activation part - sachini


            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }

    }



    public Long countAllInstructor() {
        return instructorRepository.countAllInstructor();
    }

    public Long countAllScouts(){
        return scoutRepository.countAllScouts();
    }

}
