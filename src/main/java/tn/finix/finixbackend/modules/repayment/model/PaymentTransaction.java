package tn.finix.finixbackend.modules.repayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction {

    private Long id;

    private Payment payment;

    private String gatewayProvider;

    private String gatewayTransactionId;

    /**
     * Raw callback payload from the payment gateway, stored as JSON string.
     */
    private String callbackData;

    private LocalDateTime createdAt;
}


