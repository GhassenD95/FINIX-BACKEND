package tn.finix.finixbackend.modules.security.model;

import lombok.*;


import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FinancialPeriod {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PeriodStatus status;

    private Set<KPI> kpis;
}