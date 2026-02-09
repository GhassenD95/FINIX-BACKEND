package tn.finix.finixbackend.modules.repayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.finix.finixbackend.modules.credit.model.CreditContract;
import tn.finix.finixbackend.modules.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CollectionTask {

    private Long id;

    private String taskReference;

    private DelinquencyCase delinquencyCase;

    private CreditContract contract;

    private User debtorUser;

    private User agent;

    private BigDecimal amountDue;

    private int daysOverdue;

    private CollectionTaskPriority priority;

    private CollectionTaskStatus status;

    private LocalDateTime assignedAt;

    private LocalDateTime completedAt;

    private String clientAddress;

    private String clientPhone;

    private Double latitude;

    private Double longitude;

    private String notes;
}


