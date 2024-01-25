package com.scoutcomapss.api.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")// Specify the allowed origin(s)
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  //scout registration
  @PostMapping("/scout/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody ScoutRegisterRequest request) {
    return ResponseEntity.ok(authenticationService.registerScout(request));
  }

  //scout auth
  @PostMapping("/scout/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticateScout(request));
  }

}
