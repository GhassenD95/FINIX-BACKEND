package tn.finix.finixbackend.modules.claims.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PremiumSchedule {
    private long id;
    private Integer installmentNumber;
    private LocalDateTime dueDate;
    private Double amount;
    private PremiumStatus status;
    private LocalDateTime paidDate;

    private InsurancePolicy policy;
}
