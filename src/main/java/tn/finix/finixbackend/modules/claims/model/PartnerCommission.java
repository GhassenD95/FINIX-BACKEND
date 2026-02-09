package tn.finix.finixbackend.modules.claims.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PartnerCommission {
    private long id;
    private String period; // e.g., "2026-02"
    private Double totalCollectedForPartner;
    private Double commissionAmount;
    private CommissionStatus status;

    private User partner;
    private LocalDateTime calculatedAt;
}
