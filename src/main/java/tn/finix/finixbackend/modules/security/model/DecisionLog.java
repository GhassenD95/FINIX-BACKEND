package tn.finix.finixbackend.modules.security.model;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DecisionLog {

    private Long id;
    private String decisionTaken;
    private LocalDate decidedAt;
    private String actor;
}
