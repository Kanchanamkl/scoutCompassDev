package com.scoutcomapss.api.event;

import com.scoutcomapss.api.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;


public interface EventRepository extends JpaRepository<Event, Long> {


    Optional<Event> findByEventName(String eventName);

    @Query("SELECT e FROM Event e ")
    ArrayList<Event> findAllByEventId();



    @Query("SELECT e FROM Event e ORDER BY e.eventId DESC ")
    Event getLatestEvent();
}
