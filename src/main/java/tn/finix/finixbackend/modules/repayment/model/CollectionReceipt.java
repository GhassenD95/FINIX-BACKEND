package tn.finix.finixbackend.modules.repayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CollectionReceipt {

    private Long id;

    private Payment payment;

    private CollectionTask task;

    private String photoUrl;

    private String signatureUrl;

    private Double latitude;

    private Double longitude;

    private LocalDateTime createdAt;
}


