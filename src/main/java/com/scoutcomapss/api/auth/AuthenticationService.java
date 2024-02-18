package com.scoutcomapss.api.auth;


import com.scoutcomapss.api.config.JwtService;
import com.scoutcomapss.api.auth.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public AuthenticationResponse registerInstruct(InstructRegisterRequest instructRegisterRequest) {

        Instructor instructor = Instructor.builder()
                .enabled(true)   // this boolean should be false after implementation of account activation.
                .instructFirstName(instructRegisterRequest.getInstructFirstName())
                .instructLastname(instructRegisterRequest.getInstructLastName())
                .instructEmail(instructRegisterRequest.getInstructEmail())
                .instructDob(instructRegisterRequest.getInstructDob())
                .instructGender(instructRegisterRequest.getInstructGender())
                .instructMobNum(instructRegisterRequest.getInstructMobNum())
                .instructSchool(instructRegisterRequest.getInstructSchool())
                .instructPassword(passwordEncoder.encode(instructRegisterRequest.getInstructPassword()))
                .role(Role.ROLE_INSTRUCTOR)
                .build();

        instructorRepository.save(instructor);

        User user = User.builder()
                .userName(instructRegisterRequest.getInstructEmail())
                .userPassword(passwordEncoder.encode(instructRegisterRequest.getInstructPassword()))
                .role(Role.ROLE_SCOUT)
                .build();

        userRepository.save(user);

        // todo: email verification and account activation part - sachini


        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
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


}
