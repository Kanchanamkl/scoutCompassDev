package com.scoutcomapss.api.controller;

import com.scoutcomapss.api.resource.Resource;
import com.scoutcomapss.api.resource.ResourceService;
import com.scoutcomapss.api.resource.ResourceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResourceControllerTest {

    @Mock
    private ResourceService resourceService;

    @InjectMocks
    private ResourceController resourceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUploadResourceToFIleSystem() throws IOException {
        // Given
        MultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        String expectedFileName = "filename.txt";

        // When
        when(resourceService.uploadResouceToFileSystem(file)).thenReturn(expectedFileName);
        ResponseEntity<?> responseEntity = resourceController.uploadResourceToFIleSystem(file);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFileName, responseEntity.getBody());
        verify(resourceService, times(1)).uploadResouceToFileSystem(file);
    }

    @Test
    void testDownloadResourceFromFileSystem() throws IOException {
        // Given
        String fileName = "filename.txt";
        byte[] imageData = "some image data".getBytes();

        // When
        when(resourceService.downloadResouceFromFileSystem(fileName)).thenReturn(imageData);
        ResponseEntity<?> responseEntity = resourceController.downloadResourceFromFileSystem(fileName);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(imageData, responseEntity.getBody());
        verify(resourceService, times(1)).downloadResouceFromFileSystem(fileName);
    }


    @Test
    void testDeleteResourceFromFileSystem() {
        // Given
        String fileName = "filename.txt";

        // When
        when(resourceService.deleteResource(fileName)).thenReturn(true);
        ResponseEntity<?> responseEntity = resourceController.deleteResourceFromFileSystem(fileName);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Resource deleted successfully", responseEntity.getBody());
        verify(resourceService, times(1)).deleteResource(fileName);
    }

    @Test
    void testCountAllResources() {
        // Given
        long expectedCount = 10;

        // When
        when(resourceService.countAllResources()).thenReturn(expectedCount);
        Long count = resourceController.countAllResources();

        // Then
        assertEquals(expectedCount, count);
        verify(resourceService, times(1)).countAllResources();
    }
}
