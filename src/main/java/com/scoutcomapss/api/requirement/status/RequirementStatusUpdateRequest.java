package com.scoutcomapss.api.requirement.status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequirementStatusUpdateRequest {

    private String userName;
    private Integer awardId;
    private Integer requirementId;
    private String newStatus;
}
