package com.P1.Gaser.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @NotBlank(message = "Vehicle number is required")
    @Column(unique = true, nullable = false)
    private String vehicleNumber;

    @NotBlank(message = "Vehicle brand is required")
    private String vehicleBrand;

    @NotBlank(message = "Vehicle model is required")
    private String vehicleModel;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    @Min(value = 1900, message = "Vehicle year must be valid")
    private int vehicleYear;

    @Min(value = 0, message = "KM driven cannot be negative")
    private int kmDriven;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}