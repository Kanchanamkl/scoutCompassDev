package com.scoutcomapss.api.controller;

import com.scoutcomapss.api.auth.user.Instructor;
import com.scoutcomapss.api.auth.user.Role;
import com.scoutcomapss.api.auth.user.Scout;
import com.scoutcomapss.api.auth.user.User;
import com.scoutcomapss.api.auth.AuthenticationController;
import com.scoutcomapss.api.auth.AuthenticationService;
import com.scoutcomapss.api.auth.ScoutRegisterRequest;
import com.scoutcomapss.api.auth.AuthenticationResponse;
import com.scoutcomapss.api.auth.AuthenticationRequest;
import com.scoutcomapss.api.auth.InstructRegisterRequest;
import com.scoutcomapss.api.auth.AdminRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testAuthenticateUser() {

        AuthenticationRequest request = new AuthenticationRequest("user_email", "password");
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token");


        when(authenticationService.authenticateUser(request)).thenReturn(expectedResponse);
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticateUser(request);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(authenticationService, times(1)).authenticateUser(request);
    }





    @Test
    void testGetUserByUserEmail() {

        String userEmail = "email";
        User expectedUser = new User(1,"user_email", "password",Role.ROLE_SCOUT);


        when(authenticationService.getUserByUserEmail(userEmail)).thenReturn(expectedUser);
        ResponseEntity<User> responseEntity = authenticationController.getUserByUserEmail(userEmail);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUser, responseEntity.getBody());
        verify(authenticationService, times(1)).getUserByUserEmail(userEmail);
    }



    @Test
    void testCountAllInstructors() {

        long expectedCount = 10;


        when(authenticationService.countAllInstructor()).thenReturn(expectedCount);
        Long count = authenticationController.countAllInstructors();


        assertEquals(expectedCount, count);
        verify(authenticationService, times(1)).countAllInstructor();
    }

    @Test
    void testCountAllScouts() {

        long expectedCount = 5;


        when(authenticationService.countAllScouts()).thenReturn(expectedCount);
        Long count = authenticationController.countAllScouts();


        assertEquals(expectedCount, count);
        verify(authenticationService, times(1)).countAllScouts();
    }

    @Test
    void testTestMapping() {

        String mappingResult = authenticationController.testMapping();


        assertEquals("test mapping", mappingResult);
    }
}
