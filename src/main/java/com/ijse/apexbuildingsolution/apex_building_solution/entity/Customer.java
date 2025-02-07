package com.ijse.apexbuildingsolution.apex_building_solution.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
}
