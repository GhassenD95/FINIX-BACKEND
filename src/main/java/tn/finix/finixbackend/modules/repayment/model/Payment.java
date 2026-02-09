package tn.finix.finixbackend.modules.repayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.finix.finixbackend.modules.credit.model.CreditContract;
import tn.finix.finixbackend.modules.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private Long id;

    private String paymentReference;

    private CreditContract contract;

    private User user;

    private RepaymentSchedule schedule;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private LocalDateTime paymentDate;

    private String transactionReference;

    private PaymentStatus status;

    /**
     * The user (agent or IMF admin) who processed the payment.
     * When paymentMethod is CASH_AGENT, this should correspond to the field agent.
     */
    private User processedBy;

    private String receiptUrl;

    private String notes;
}


