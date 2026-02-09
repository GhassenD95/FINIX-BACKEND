package tn.finix.finixbackend.modules.repayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.finix.finixbackend.modules.credit.model.CreditContract;
import tn.finix.finixbackend.modules.user.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GracePeriod {

    private Long id;

    private CreditContract contract;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

    private User approvedBy;

    private LocalDateTime approvedAt;
}


