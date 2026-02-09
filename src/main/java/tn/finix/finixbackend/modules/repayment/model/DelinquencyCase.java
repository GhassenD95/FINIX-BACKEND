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
public class DelinquencyCase {

    private Long id;

    private String caseNumber;

    private CreditContract contract;

    private BigDecimal totalDue;
    private BigDecimal principalDue;
    private BigDecimal interestDue;
    private BigDecimal penaltiesDue;

    private int daysOverdue;

    private DelinquencyLevel delinquencyLevel;

    private DelinquencyStatus status;

    private LocalDateTime lastContactDate;

    private LocalDate nextActionDate;

    private String resolutionType;
}


