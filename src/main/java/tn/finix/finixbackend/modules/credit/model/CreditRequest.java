package tn.finix.finixbackend.modules.credit.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequest {

    private long id;

    // Requesting user (role = CLIENT)
    private User user;

    private Long vehicleId;

    private Double requestedAmount;
    private Integer durationMonths;

    private CreditRequestStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Set<CreditDocument> documents;
    private RiskScore riskScore;
}
