package tn.finix.finixbackend.modules.vehicle.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeliveryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    @ToString.Exclude
    private VehicleSale sale;

    private LocalDateTime scheduledDate;
    private String location;
    private String contactPerson;

    private String status; // SCHEDULED, IN_TRANSIT, DELIVERED

    private String proofOfDeliveryUrl;
    private LocalDateTime deliveredAt;
}
