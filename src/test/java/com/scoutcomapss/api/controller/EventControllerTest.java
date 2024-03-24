package com.scoutcomapss.api.controller;

import com.scoutcomapss.api.event.Event;
import com.scoutcomapss.api.event.EventCreateRequest;
import com.scoutcomapss.api.event.EventController;
import com.scoutcomapss.api.event.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testGetEventList() {
        // Given
        ArrayList<Event> expectedList = new ArrayList<>();
        expectedList.add(new Event(1L,"eventName1", "eventDate","Duration","location","time","description","form_link"));
        expectedList.add(new Event(2l,"eventName2", "eventDate","Duration","location","time","description","form_link"));

        // When
        when(eventService.getEventList()).thenReturn(expectedList);
        ResponseEntity<?> responseEntity = eventController.getEventList();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedList, responseEntity.getBody());
        verify(eventService, times(1)).getEventList();
    }

    @Test
    void testDeleteEvent() {
        // Given
        String eventName = "eventName";
        boolean deletionStatus = true;

        // When
        when(eventService.deleteEvent(eventName)).thenReturn(deletionStatus);
        ResponseEntity<?> responseEntity = eventController.deleteEvent(eventName);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("event deleted successfully", responseEntity.getBody());
        verify(eventService, times(1)).deleteEvent(eventName);
    }

    @Test
    void testDeleteEvent_NotFound() {
        // Given
        String eventName = "eventName";
        boolean deletionStatus = false;

        // When
        when(eventService.deleteEvent(eventName)).thenReturn(deletionStatus);
        ResponseEntity<?> responseEntity = eventController.deleteEvent(eventName);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("event not found or unable to delete", responseEntity.getBody());
        verify(eventService, times(1)).deleteEvent(eventName);
    }

    @Test
    void testGetLatestEvent() {
        // Given
        Event expectedEvent = new Event(1L,"eventName", "eventDate","Duration","location","time","description","form_link");

        // When
        when(eventService.getLatestEvent()).thenReturn(expectedEvent);
        ResponseEntity<Event> responseEntity = eventController.getLatestEvent();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEvent, responseEntity.getBody());
        verify(eventService, times(1)).getLatestEvent();
    }

    @Test
    void testCountAllEvents() {
        // Given
        long expectedCount = 5;

        // When
        when(eventService.countAllEvents()).thenReturn(expectedCount);
        Long count = eventController.countAllEvents();

        // Then
        assertEquals(expectedCount, count);
        verify(eventService, times(1)).countAllEvents();
    }
}
