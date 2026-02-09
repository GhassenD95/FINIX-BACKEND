package tn.finix.finixbackend.modules.claims.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClaimAssessment {
    private long id;
    private LocalDateTime assessmentDate;
    private String expertNotes;
    private Double estimatedRepairCost;
    private Boolean isCovered;

    private Claim claim;
    private User expert;
}
