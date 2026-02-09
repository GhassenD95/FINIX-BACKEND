package tn.finix.finixbackend.modules.security.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KPI {

    private Long id;
    private String code;
    private String label;
    private Double value;
    private Double threshold;
    private KPIStatus status;
    private LocalDate calculatedAt;

    private FinancialDashboard dashboard;
    private FinancialPeriod period;
}