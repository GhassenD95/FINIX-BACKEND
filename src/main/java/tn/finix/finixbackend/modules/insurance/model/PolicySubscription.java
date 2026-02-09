package tn.finix.finixbackend.modules.insurance.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PolicySubscription {
    private Long id;
    private boolean termsAccepted;
    private String signatureUrl;
    private SubscriptionStatus statut;

    private Quote quote;
}
