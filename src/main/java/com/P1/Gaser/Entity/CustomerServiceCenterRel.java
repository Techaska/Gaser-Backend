package com.P1.Gaser.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "customer_service_center_rel",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"customer_id", "service_center_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceCenterRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "service_center_id", nullable = false)
    private ServiceCenter serviceCenter;
}