package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.ProjectMaterialFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.ProjectMaterialDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectMaterialsDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.ProjectMaterials;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectMaterialFormBOImpl implements ProjectMaterialFormBO {

    ProjectMaterialDAO projectMaterialDAO = (ProjectMaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROJECTMATERIAL);

    public String getNextProjectID() throws SQLException, ClassNotFoundException {
        return projectMaterialDAO.getNextId();
    }
    public boolean saveMaterialProject(ProjectMaterialsDto materialProjectDto) throws SQLException, ClassNotFoundException {
        ArrayList<ProjectMaterialsDto> projectMaterialsDtos = new ArrayList<>();
        ArrayList<ProjectMaterials> projectMaterials = projectMaterialDAO.getAll();
        for (ProjectMaterials projectMaterial : projectMaterials) {
            projectMaterialsDtos.add(new ProjectMaterialsDto(projectMaterial.getProjectId(),projectMaterial.getMaterialId(),projectMaterial.getQty()));
        }
        return projectMaterialsDtos.add(materialProjectDto);
    }

    public boolean deleteMaterialProject(String projectId) throws SQLException, ClassNotFoundException {
        return projectMaterialDAO.delete(projectId);
    }

    public boolean updateMaterialProject(ProjectMaterialsDto materialProjectDto) throws SQLException, ClassNotFoundException {
        return projectMaterialDAO.update(new ProjectMaterials(materialProjectDto.getProjectId(),materialProjectDto.getMaterialId(),materialProjectDto.getQty()));
    }

    public ArrayList<ProjectMaterialsDto> searchMaterialProject(String projectId) throws SQLException {

        ArrayList<ProjectMaterialsDto> projectMaterialsDtos = new ArrayList<>();
        ArrayList<ProjectMaterials> projectMaterials = projectMaterialDAO.searchMaterialProject(projectId);

        for (ProjectMaterials projectMaterial : projectMaterials) {
            projectMaterialsDtos.add(new ProjectMaterialsDto(
                    projectMaterial.getProjectId(),
                    projectMaterial.getMaterialId(),
                    projectMaterial.getQty()
            ));
        }
        return projectMaterialsDtos;

    }
    public ArrayList<ProjectMaterialsDto> getAllProjectMaterialDetails() throws SQLException, ClassNotFoundException {

        ArrayList<ProjectMaterialsDto> projectMaterialsDtos = new ArrayList<>();
        ArrayList<ProjectMaterials> projectMaterials = projectMaterialDAO.getAll();

        for (ProjectMaterials projectMaterial : projectMaterials) {
            projectMaterialsDtos.add(new ProjectMaterialsDto(
                    projectMaterial.getProjectId(),
                    projectMaterial.getMaterialId(),
                    projectMaterial.getQty()
            ));
        }
        return projectMaterialsDtos;

    }
}
