package com.scoutcomapss.api.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/scoutcompass/event")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    //scout registration
    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody EventCreateRequest request) {
        return ResponseEntity.ok().body(eventService.createEvent(request));
    }

    @GetMapping("/eventList")
    public ResponseEntity<?> getEventList(){
        ArrayList<Event> eventList = eventService.getEventList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventList);
    }

    @DeleteMapping("/delete/{eventName}")
    public ResponseEntity<?> deleteEvent(@PathVariable String eventName) {
        boolean deletionStatus = eventService.deleteEvent(eventName);

        if (deletionStatus) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("event deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("event not found or unable to delete");
        }
    }

    @GetMapping("/latestEvent")
    public ResponseEntity<Event> getLatestEvent(){
        Event event  = eventService.getLatestEvent();
        return ResponseEntity.status(HttpStatus.OK)
                .body(event);
    }

    @GetMapping("/count")
    public Long countAllEvents() {
        return eventService.countAllEvents();
    }

}
