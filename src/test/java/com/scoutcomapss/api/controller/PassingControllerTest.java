package com.scoutcomapss.api.controller;

import com.scoutcomapss.api.auth.user.Scout;
import com.scoutcomapss.api.requirement.Requirement;
import com.scoutcomapss.api.requirement.status.RequirementStatus;
import com.scoutcomapss.api.passing.PassingController;
import com.scoutcomapss.api.passing.PassingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PassingControllerTest {

    @Mock
    private PassingService passingService;

    @InjectMocks
    private PassingController passingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testGetScoutListByInstructorUserName_NotFound() {
        // Given
        String instructorEmail = "instructor@example.com";

        // When
        when(passingService.getScoutListByInstructorEmail(instructorEmail)).thenReturn(null);
        ResponseEntity<?> responseEntity = passingController.getScoutListByInstructorUserName(instructorEmail);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Scouts not found under the instructor!", responseEntity.getBody());
        verify(passingService, times(1)).getScoutListByInstructorEmail(instructorEmail);
    }



    @Test
    void testGetRequirementsByScout_NotFound() {
        // Given
        String scoutEmail = "scout@example.com";

        // When
        when(passingService.getRequirementStatusListByScout(scoutEmail)).thenReturn(null);
        ResponseEntity<?> responseEntity = passingController.getRequirementsByScout(scoutEmail);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("No Pending requirements!", responseEntity.getBody());
        verify(passingService, times(1)).getRequirementStatusListByScout(scoutEmail);
    }

    @Test
    void testTestMapping() {
        // When
        String mappingResult = passingController.testMapping();

        // Then
        assertEquals("test mapping", mappingResult);
    }
}
