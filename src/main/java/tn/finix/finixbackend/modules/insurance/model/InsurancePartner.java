package tn.finix.finixbackend.modules.insurance.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePartner {
    private Long id;
    private String companyName;
    private PartnerStatus statut;
    private double commissionPct;
    private double rating;
}
