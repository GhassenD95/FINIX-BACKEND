package tn.finix.finixbackend.modules.credit.model;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditContract {

    private Long id;

    private String contractNumber;

    private CreditRequest creditRequest;

    private BigDecimal amount;
    private BigDecimal interestRate;
    private int durationMonths;
    private BigDecimal monthlyPayment;
    private BigDecimal totalToRepay;

    private LocalDate startDate;
    private LocalDate endDate;

    private CreditContractStatus status;

    private String pdfUrl;
}