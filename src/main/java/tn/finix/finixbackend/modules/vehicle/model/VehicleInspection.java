package tn.finix.finixbackend.modules.vehicle.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @ToString.Exclude
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspector_id") // Agent who inspected
    @ToString.Exclude
    private User inspector;

    private LocalDateTime inspectionDate;
    private String reportUrl;
    private String overallCondition;

    // JSON or simple text fields for detailed metrics
    private String mechanicalMetrics;
    private String bodyMetrics;
}
