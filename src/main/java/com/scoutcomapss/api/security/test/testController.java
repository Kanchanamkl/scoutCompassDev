package com.scoutcomapss.api.security.test;

import com.scoutcomapss.api.security.auth.AuthenticationRequest;
import com.scoutcomapss.api.security.auth.AuthenticationResponse;
import com.scoutcomapss.api.security.auth.AuthenticationService;
import com.scoutcomapss.api.security.auth.ScoutRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**

 * @author : Kanchana Kalansooriya

 * @since 2/5/2024

 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")// Specify the allowed origin(s)
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class testController {


    @GetMapping("/t1")
    public String test() {
        return "test_test";
    }

}
