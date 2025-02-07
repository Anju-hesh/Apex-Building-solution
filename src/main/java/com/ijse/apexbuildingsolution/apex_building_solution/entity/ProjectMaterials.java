package com.ijse.apexbuildingsolution.apex_building_solution.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProjectMaterials {
    private String projectId;
    private String materialId;
    private int qty;
}
