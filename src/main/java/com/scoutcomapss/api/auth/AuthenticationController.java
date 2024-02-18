package com.scoutcomapss.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")// Specify the allowed origin(s)
//@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  //scout registration
  @PostMapping("/scout/register")
  public ResponseEntity<AuthenticationResponse> registerScout(@RequestBody ScoutRegisterRequest request) {
    return ResponseEntity.ok(authenticationService.registerScout(request));
  }

  //scout auth
  @PostMapping("/user/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticateUser(request));
  }

  //instructor registration
  @PostMapping("/instruct/register")
  public ResponseEntity<AuthenticationResponse> registerInstruct(@RequestBody InstructRegisterRequest request){
    return ResponseEntity.ok(authenticationService.registerInstruct(request));
  }


  @GetMapping
  public String testMapping(){
    return "test mapping";
  }


}
