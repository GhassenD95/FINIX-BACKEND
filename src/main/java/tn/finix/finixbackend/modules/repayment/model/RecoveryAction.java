package tn.finix.finixbackend.modules.repayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.finix.finixbackend.modules.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryAction {

    private Long id;

    private CollectionTask task;

    private RecoveryActionType actionType;

    private User performedBy;

    private LocalDateTime performedAt;

    private RecoveryActionResult result;

    private BigDecimal amountCollected;

    private LocalDate promiseDate;

    private String notes;

    private String proofPhotoUrl;

    private Double latitude;

    private Double longitude;
}


