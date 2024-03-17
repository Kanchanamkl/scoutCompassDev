package com.scoutcomapss.api.requirement.status;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "requirement_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequirementStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private Integer awardId;
    private Integer requirementId;
    private Integer marks;
    private String status;
}
