package com.scoutcomapss.api.security.auth;


import com.scoutcomapss.api.security.config.JwtService;
import com.scoutcomapss.api.user.Role;
import com.scoutcomapss.api.user.Scout;
import com.scoutcomapss.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse registerScout(ScoutRegisterRequest scoutRegisterRequest) {
    var user = Scout.builder()
        .scoutFirstName(scoutRegisterRequest.getScoutFirstName())
        .scoutLastname(scoutRegisterRequest.getScoutLastname())
        .scoutEmail(scoutRegisterRequest.getScoutEmail())
        .scoutDob(scoutRegisterRequest.getScoutDob())
        .scoutGender(scoutRegisterRequest.getScoutGender())
        .scoutMobNum(scoutRegisterRequest.getScoutMobNum())
        .scoutSchool(scoutRegisterRequest.getScoutSchool())
        .scoutPassword(passwordEncoder.encode(scoutRegisterRequest.getScoutPassword()))
        .instructorId(scoutRegisterRequest.getInstructorId())
        .enabled(false)
        .role(Role.USER)
        .build();

    repository.save(user);


    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }



  public AuthenticationResponse authenticateScout(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    var user = repository.findByScoutEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }
}
