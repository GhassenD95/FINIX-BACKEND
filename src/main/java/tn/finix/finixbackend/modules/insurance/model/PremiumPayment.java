package tn.finix.finixbackend.modules.insurance.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PremiumPayment {
    private long id;
    private LocalDateTime paymentDate;
    private Double amountPaid;
    private String paymentMethod; // D17, CARD, CASH
    private String transactionReference;

    private User user;
    private PremiumSchedule schedule;
}
