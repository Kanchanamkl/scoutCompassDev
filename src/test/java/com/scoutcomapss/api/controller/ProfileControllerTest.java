package com.scoutcomapss.api.controller;

import com.scoutcomapss.api.auth.user.Instructor;
import com.scoutcomapss.api.profile.ProfileService;
import com.scoutcomapss.api.auth.user.Scout;
import com.scoutcomapss.api.profile.ProfileController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testGetScoutByEmail_NotFound() {
        // Given
        String scoutEmail = "scout@example.com";

        // When
        when(profileService.getScoutByEmail(scoutEmail)).thenReturn(null);
        ResponseEntity<?> responseEntity = profileController.getScoutByEmail(scoutEmail);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Scout not found!", responseEntity.getBody());
        verify(profileService, times(1)).getScoutByEmail(scoutEmail);
    }



    @Test
    void testGetInstructorByEmail_NotFound() {
        // Given
        String instructorEmail = "instructor@example.com";

        // When
        when(profileService.getInstructorByEmail(instructorEmail)).thenReturn(null);
        ResponseEntity<?> responseEntity = profileController.getInstructorByEmail(instructorEmail);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("instructor not found!", responseEntity.getBody());
        verify(profileService, times(1)).getInstructorByEmail(instructorEmail);
    }
}
