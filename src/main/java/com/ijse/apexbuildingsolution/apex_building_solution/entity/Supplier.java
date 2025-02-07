package com.ijse.apexbuildingsolution.apex_building_solution.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Supplier {
    private String supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierEmail;
    private String supplierPhone;
}
