package tn.finix.finixbackend.modules.insurance.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;
import tn.finix.finixbackend.modules.credit.model.CreditContract;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicy {
    private Long id;
    private String policyNumber;
    private BigDecimal primeMensuelle;
    private BigDecimal primeAnnuelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private PolicyStatus statut;
    private String certificateUrl;
    private String raisonAnnulation;

    private User user;
    private InsuranceProduct product;
    private CreditContract creditContract;
    private InsurancePartner partner;
}
