package tn.finix.finixbackend.modules.credit.model;


import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditHistory {

    private Long id;

    private User user;

    private int totalCredits;
    private int paymentIncidents;
    private BigDecimal delayRate;

    private LocalDateTime lastUpdated;
}
