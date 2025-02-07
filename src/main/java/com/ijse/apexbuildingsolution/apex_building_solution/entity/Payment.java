package com.ijse.apexbuildingsolution.apex_building_solution.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Payment {
    private String paymentId;
    private String paymentMethod;
    private double fullBalance;
    private double payedBalance;
    private String projectId;
    private String status;
}
