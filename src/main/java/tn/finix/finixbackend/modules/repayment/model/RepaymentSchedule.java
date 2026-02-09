package tn.finix.finixbackend.modules.repayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.finix.finixbackend.modules.credit.model.CreditContract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentSchedule {

    private Long id;

    private CreditContract contract;

    private int installmentNumber;

    private LocalDate dueDate;

    private BigDecimal principalAmount;
    private BigDecimal interestAmount;
    private BigDecimal totalAmount;

    private BigDecimal amountPaid;
    private BigDecimal balanceRemaining;

    private RepaymentScheduleStatus status;

    private LocalDateTime paymentDate;

    private int daysOverdue;
}


