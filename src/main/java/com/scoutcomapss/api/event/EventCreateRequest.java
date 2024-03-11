package com.scoutcomapss.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateRequest {

    private String eventName;
    private Date eventDate;
    private Integer dayDuration;
    private String eventLocation;

}
