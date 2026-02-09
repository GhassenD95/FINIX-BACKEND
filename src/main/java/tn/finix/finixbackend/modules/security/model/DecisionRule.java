package tn.finix.finixbackend.modules.security.model;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DecisionRule {

    private Long id;
    private String ruleName;
    private String conditionExpression;
    private String action;
    private Boolean active;
}
