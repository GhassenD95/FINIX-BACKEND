package tn.finix.finixbackend.modules.security.model;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FinancialAlert {

    private Long id;
    private AlertSeverity severity;
    private String message;
    private LocalDate triggeredAt;
    private Boolean resolved;

    private KPI kpi;
    private DecisionRule decisionRule;
    private DecisionLog decisionLog;
}