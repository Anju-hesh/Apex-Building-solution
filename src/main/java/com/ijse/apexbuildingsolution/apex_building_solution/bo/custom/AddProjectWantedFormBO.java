package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;

import java.sql.SQLException;

public interface AddProjectWantedFormBO extends SuperBO {
    boolean saveProject(AddProjectWantedDto addProjectWantedDto) throws SQLException;
}
