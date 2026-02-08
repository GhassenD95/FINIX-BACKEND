package tn.finix.finixbackend.modules.insurance.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicy {
    private long id;
    private String policyNumber;
    private User user;
    private String status; // PENDING, ACTIVE, EXPIRED, CANCELLED
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
