package tn.finix.finixbackend.modules.insurance.model;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CoverageOption {
    private Long id;
    private CoverageType type;
    private BigDecimal limiteCouverture;
    private BigDecimal franchise;
    private boolean incluse;
    private BigDecimal coutAdditionnel;
}
