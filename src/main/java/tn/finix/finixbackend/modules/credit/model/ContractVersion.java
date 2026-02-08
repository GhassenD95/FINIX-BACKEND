package tn.finix.finixbackend.modules.credit.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContractVersion {

    private Long id;

    private CreditContract creditContract;

    private int versionNumber;
    private String changeSummary;
    private String oldValue;
    private String newValue;

    private LocalDateTime versionDate;
}