package com.scoutcomapss.api.requirement;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/21/2024
 */

import com.scoutcomapss.api.requirement.status.RequirementStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_requirement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Requirement {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer awardId;
    private Integer requirementId;
    private String englishName;
    private String sinhalaName;
    private Integer isPracticalRequirement;

    @OneToMany(cascade =CascadeType.ALL)
    List<RequirementStatus> requirementStatus =  new ArrayList<>();

}
