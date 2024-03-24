package com.scoutcomapss.api.requirement.status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate completedDate;
    private Integer marks;
    private String status;
}
