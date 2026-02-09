package tn.finix.finixbackend.modules.insurance.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;
import tn.finix.finixbackend.modules.vehicle.model.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private Long id;
    private BigDecimal primeMensuelle;
    private BigDecimal primeAnnuelle;
    private LocalDate validiteJusqua;
    private QuoteStatus statut;

    private User user;
    private Vehicle vehicle;
    private InsuranceProduct product;
}
