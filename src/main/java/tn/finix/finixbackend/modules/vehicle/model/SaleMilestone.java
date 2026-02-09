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
public class SaleMilestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    @ToString.Exclude
    private VehicleSale sale;

    private String stepName; // e.g., "DOCS_UPLOADED", "FUNDS_SECURED"
    private String status; // PENDING, COMPLETED, FAILED
    private LocalDateTime completedAt;
    private String updatedBy; // Username or Agent ID
}
