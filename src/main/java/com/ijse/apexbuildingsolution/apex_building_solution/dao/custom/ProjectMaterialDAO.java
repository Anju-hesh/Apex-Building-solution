package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectMaterialsDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.ProjectMaterials;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProjectMaterialDAO extends CrudDAO<ProjectMaterials> {
//     String getNextProjectID() throws SQLException ;
//     boolean saveMaterialProject(ProjectMaterialsDto materialProjectDto) throws SQLException ;
//     boolean deleteMaterialProject(String projectId) throws SQLException ;
//     boolean updateMaterialProject(ProjectMaterialsDto materialProjectDto) throws SQLException ;
     ArrayList<ProjectMaterials> searchMaterialProject(String projectId) throws SQLException ;
//     ArrayList<ProjectMaterialsDto> getAllProjectMaterialDetails() throws SQLException ;
      boolean saveProjectMaterials(AddProjectWantedDto addProjectWantedDto) throws SQLException ;

}
