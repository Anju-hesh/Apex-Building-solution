package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectMaterialsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProjectMaterialFormBO extends SuperBO {

     String getNextProjectID() throws SQLException, ClassNotFoundException;
     boolean saveMaterialProject(ProjectMaterialsDto materialProjectDto) throws SQLException, ClassNotFoundException;
     boolean deleteMaterialProject(String projectId) throws SQLException, ClassNotFoundException;
     boolean updateMaterialProject(ProjectMaterialsDto materialProjectDto) throws SQLException, ClassNotFoundException;
     ArrayList<ProjectMaterialsDto> searchMaterialProject(String projectId) throws SQLException ;
     ArrayList<ProjectMaterialsDto> getAllProjectMaterialDetails() throws SQLException, ClassNotFoundException;
}
