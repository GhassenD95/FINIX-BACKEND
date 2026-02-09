package tn.finix.finixbackend.modules.vehicle.model;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private VehicleType type;
    private BigDecimal valeur;
    private int annee;
}
