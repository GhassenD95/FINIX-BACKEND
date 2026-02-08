package tn.finix.finixbackend.modules.claims.model;

import lombok.*;
import tn.finix.finixbackend.modules.insurance.model.InsurancePolicy;
import tn.finix.finixbackend.modules.user.model.User;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    private long id;
    private String claimNumber;
    private String description;
    private LocalDateTime incidentDate;
    private LocalDateTime reportedDate;
    private String location;

    private ClaimStatus status;
    private ClaimType claimType;

    private Double totalDamageAmount;
    private Double approvedPayoutAmount;
    private Integer fraudScore; // 0..100

    private User user;
    private InsurancePolicy policy;
    private Long vehicleId; // Link to Vehicle module

    private Set<ClaimDocument> documents;
    private ClaimAssessment assessment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
