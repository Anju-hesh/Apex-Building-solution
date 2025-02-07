package com.ijse.apexbuildingsolution.apex_building_solution.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Repair {
    private String repairId;
    private String machineId;
    private int qty;
}
