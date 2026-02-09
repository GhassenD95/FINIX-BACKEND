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
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @ToString.Exclude
    private User seller;

    private String brand;
    private String model;
    private int year;
    private BigDecimal price;
    private int mileage;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmission;

    private String description;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<VehicleImage> images;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<VehicleDocument> documents;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
