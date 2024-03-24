package com.scoutcomapss.api.requirement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequirementRegisterRequest {

    private Integer awardId;
    private Integer requirementId;
    private String englishName;
    private String sinhalaName;
    private Integer isPracticalRequirement;

}
