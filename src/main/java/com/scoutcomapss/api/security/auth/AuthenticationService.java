package com.scoutcomapss.api.security.auth;


import com.scoutcomapss.api.security.config.JwtService;
import com.scoutcomapss.api.user.*;
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

        // todo: email verification and account activation part - sachini


    var jwtToken = jwtService.generateToken(scout);
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

    // todo: email verification and account activation part - sachini


    var jwtToken = jwtService.generateToken(instructor);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }



  public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
    System.out.println("authenticateUser");
   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    // todo: validate user account activation before login
    System.out.println("isScoutPresent-");
    boolean isScoutPresent = scoutRepository.findByScoutEmail(request.getEmail()).isPresent();
    System.out.println("isScoutPresent"+ isScoutPresent);
    boolean isInstructPresent = instructorRepository.findByInstructEmail(request.getEmail()).isPresent();
    if(isScoutPresent){
      System.out.println("isScoutPresent");
      var scout = scoutRepository.findByScoutEmail(request.getEmail()).orElseThrow(() -> new IllegalStateException("scout not found"));
      String  jwtToken = jwtService.generateToken(scout);
      return AuthenticationResponse.builder().token(jwtToken).build();
    }else if(isInstructPresent){
      Instructor instructor = instructorRepository.findByInstructEmail(request.getEmail()).orElseThrow(() -> new IllegalStateException("Instructor not found"));
      var  jwtToken = jwtService.generateToken(instructor);
      return AuthenticationResponse.builder().token(jwtToken).build();
    }
    System.out.println("!isScoutPresent");
    return null;
  }


}
