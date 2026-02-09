package tn.finix.finixbackend.modules.vehicle.model;

import lombok.*;
import tn.finix.finixbackend.modules.user.model.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String saleNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @ToString.Exclude
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    @ToString.Exclude
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @ToString.Exclude
    private User seller;

    private BigDecimal agreedPrice;

    @Enumerated(EnumType.STRING)
    private SaleStatus status;

    private String contractUrl;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<SaleMilestone> milestones;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<SaleMessage> messages;

    @OneToOne(mappedBy = "sale", cascade = CascadeType.ALL)
    @ToString.Exclude
    private DeliveryInfo deliveryInfo;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
