package com.scoutcomapss.api.requirement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequirementStatusResponse {
    private Integer awardId;
    private Integer requirementId;
    private Integer isPracticalRequirement;
    private String sinhalaName;
    private String englishName;
    private Integer marks;
    private String status;
}
