package com.scoutcomapss.api.requirement.status;



import com.scoutcomapss.api.requirement.Requirement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "requirement_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequirementStatus {
    @Id

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String userName;
    private Integer awardId;
    private Integer requirementId;
    private Integer marks;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "requirement")
    public Requirement requirement;
}
