package tn.finix.finixbackend.modules.credit.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RiskScore {

    private Long id;

    private int trustScore;               // 0..35
    private int dtiScore;                 // 0..25
    private int paymentHistoryScore;      // 0..20
    private int downPaymentScore;         // 0..10
    private int socialApprovalScore;      // 0..10

    private int totalScore;               // 0..100
    private RiskLevel level;

    private LocalDateTime calculatedAt;
}
