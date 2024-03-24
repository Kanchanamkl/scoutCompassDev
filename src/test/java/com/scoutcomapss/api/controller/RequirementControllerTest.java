package com.scoutcomapss.api.controller;

import com.scoutcomapss.api.requirement.Requirement;
import com.scoutcomapss.api.requirement.RequirementService;
import com.scoutcomapss.api.requirement.RequirementController;
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

class RequirementControllerTest {

    @Mock
    private RequirementService requirementService;

    @InjectMocks
    private RequirementController requirementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRequirementsByAwardId() {
        // Given
        int awardId = 1;
        List<Requirement> expectedRequirements = new ArrayList<>();
        Requirement requirement1 = new Requirement();
        Requirement requirement2 = new Requirement();
        expectedRequirements.add(requirement1);
        expectedRequirements.add(requirement2);

        // When
        when(requirementService.getRequirementsByAwardId(awardId)).thenReturn(expectedRequirements);
        ResponseEntity<?> responseEntity = requirementController.getRequirementsByAwardId(awardId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedRequirements, responseEntity.getBody());
        verify(requirementService, times(1)).getRequirementsByAwardId(awardId);
    }
}

