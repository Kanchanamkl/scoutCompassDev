package com.scoutcomapss.api.auth;

import com.scoutcomapss.api.auth.user.Instructor;
import com.scoutcomapss.api.auth.user.Scout;
import com.scoutcomapss.api.auth.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")// Specify the allowed origin(s)
//@CrossOrigin(origins = "*")
@RequestMapping("/api/scoutcompass/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  //scout registration
  @PostMapping("/scout/register")
  public ResponseEntity<AuthenticationResponse> registerScout(@RequestBody ScoutRegisterRequest request) {
    if(authenticationService.registerScout(request)!=null){
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(authenticationService.registerScout(request));
    }else{
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(null);
    }

  }

  //scout auth
  @PostMapping("/user/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticateUser(request));
  }

  //instructor registration
  @PostMapping("/instruct/register")
  public ResponseEntity<AuthenticationResponse> registerInstruct(@RequestBody InstructRegisterRequest request){
    if(authenticationService.registerInstruct(request)!=null){
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(authenticationService.registerInstruct(request));
    }else{
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(null);
    }
  }


  @GetMapping
  public String testMapping(){
    return "test mapping";
  }

  @GetMapping("/instructorList")
  public ResponseEntity<?> getInstructorList(){
    List<Instructor> instructorList = authenticationService.getInstructorList();
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(instructorList);
  }

  @GetMapping("/user")
  public ResponseEntity<User> getUserByUserEmail(@RequestParam String userEmail){

     User user = authenticationService.getUserByUserEmail(userEmail);
      return ResponseEntity.
              status(HttpStatus.OK)
              .body(user);
  }



  @PostMapping("/admin/register")
  public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody AdminRegisterRequest adminRegisterRequest) {
    if(authenticationService.registerAdmin(adminRegisterRequest)!=null){
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(authenticationService.registerAdmin(adminRegisterRequest));
    }else{
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(null);
    }

  }

  @GetMapping("/instructor/count")
  public Long countAllInstructors() {
    return authenticationService.countAllInstructor();
  }

  @GetMapping("/scout/count")
  public Long countAllScouts() {
    return authenticationService.countAllScouts();
  }

}
