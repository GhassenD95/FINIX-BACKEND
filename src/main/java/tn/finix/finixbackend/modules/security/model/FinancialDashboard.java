package tn.finix.finixbackend.modules.security.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FinancialDashboard {

    private Long id;
    private String name;
    private LocalDate createdAt;

    private Set<KPI> kpis;
}
