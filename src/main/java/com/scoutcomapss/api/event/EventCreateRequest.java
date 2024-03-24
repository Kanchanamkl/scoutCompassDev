package com.scoutcomapss.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateRequest {

    private String eventName;
    private String eventDate;
    private String dayDuration;
    private String eventLocation;
    private String startTime;
    private String description;
    private String formLink;

}
