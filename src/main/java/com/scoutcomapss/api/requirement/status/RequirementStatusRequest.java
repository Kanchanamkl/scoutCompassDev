package com.scoutcomapss.api.requirement.status;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequirementStatusRequest {

    private String userName;
    private Integer awardId;
    private Integer requirementId;
    private Integer marks;
    private String status;
}
