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
public class SaleMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    @ToString.Exclude
    private VehicleSale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    @ToString.Exclude
    private User sender;

    private String messageContent;
    private LocalDateTime sentAt;
    private Boolean isSystemMessage;

    @PrePersist
    protected void onCreate() {
        sentAt = LocalDateTime.now();
    }
}
