package tn.finix.finixbackend.modules.insurance.model;

import lombok.*;
import tn.finix.finixbackend.modules.vehicle.model.VehicleType;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceProduct {
    private Long id;
    private String nom;
    private String description;
    private VehicleType typeVehicule;
    private BigDecimal tauxBasePct;
    private ProductStatus statut;
    private String conditionsPdfUrl;
    private boolean popular;
    private boolean recommended;

    private InsurancePartner partner;
    private Set<CoverageOption> coverageOptions;
}
