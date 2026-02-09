package tn.finix.finixbackend.modules.vehicle.model;

public enum SaleStatus {
    INITIATED,
    APPROVED, // Credit approved if applicable, or offer accepted
    PAID, // Funds received/escrowed
    IN_DELIVERY,
    COMPLETED,
    CANCELLED
}
