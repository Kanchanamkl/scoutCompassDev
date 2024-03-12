package com.scoutcomapss.api.event;

import com.scoutcomapss.api.auth.AuthenticationResponse;
import com.scoutcomapss.api.auth.ScoutRegisterRequest;
import com.scoutcomapss.api.resource.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/scoutcompass/event")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EventController {

    private final EventServices eventServices;

    //scout registration
    @PostMapping("/create")
    public ResponseEntity<?> registerScout(@RequestBody EventCreateRequest request) {
        return ResponseEntity.ok().body(eventServices.createEvent(request));
    }

    @GetMapping("/eventList")
    public ResponseEntity<?> getEventList(){
        ArrayList<Event> eventList = eventServices.getEventList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventList);
    }

    @DeleteMapping("/delete/{eventName}")
    public ResponseEntity<?> deleteEvent(@PathVariable String eventName) {
        boolean deletionStatus = eventServices.deleteEvent(eventName);

        if (deletionStatus) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("event deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("event not found or unable to delete");
        }
    }

}
