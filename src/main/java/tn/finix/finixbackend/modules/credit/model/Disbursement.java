<<<<<<< HEAD
package tn.finix.finixbackend.modules.credit.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Disbursement {

    private Long id;

    private CreditContract creditContract;

    private BigDecimal amount;
    private String iban;

    private DisbursementMethod method;
    private DisbursementStatus status;

    private LocalDateTime scheduledDate;
    private LocalDateTime processedDate;

    private String bankReference;
    private String beneficiary; // SELLER / CLIENT
=======
package tn.finix.finixbackend.modules.credit.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Disbursement {

    private Long id;

    private CreditContract creditContract;

    private BigDecimal amount;
    private String iban;

    private DisbursementMethod method;
    private DisbursementStatus status;

    private LocalDateTime scheduledDate;
    private LocalDateTime processedDate;

    private String bankReference;
    private String beneficiary; // SELLER / CLIENT
>>>>>>> origin/feature/vehicle-seller-modules
}