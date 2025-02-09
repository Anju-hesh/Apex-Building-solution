package com.ijse.apexbuildingsolution.apex_building_solution.entity.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.entity.MachineProject;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Payment;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.ProjectMaterials;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AddProjectWantedCustom {
    private String projectId;
    private String projectName;
    private String projectDescription;
    private String customerId;
    private Date projectStartDate;
    private Date projectEndDate;
    private String userId;

 //   private ArrayList<ProjectDto> projectDtos;
    private ArrayList<ProjectMaterials> projectMaterialsDtos;
    private ArrayList<MachineProject> machineProjectDtos;
    private ArrayList<Payment> paymentDtos;

}
