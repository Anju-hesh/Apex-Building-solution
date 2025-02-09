package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.ProjectMaterialDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectMaterialsDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.ProjectMaterials;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.custom.AddProjectWantedCustom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectMaterialDAOImpl implements ProjectMaterialDAO {

    public String getNextId() throws SQLException {
        String sql = "SELECT ProjectID FROM projectmaterialdetails ORDER BY ProjectID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String projectID = resultSet.getString(1);
            String subprojectID = projectID.substring(4);
            int lastIdIndex = Integer.parseInt(subprojectID);
            int nextIndex = lastIdIndex + 1;
            String newId = String.format("PROJ%03d", nextIndex);
            return newId;
        }
        return "PROJ001";
    }

    @Override
    public ProjectMaterials search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(ProjectMaterials materialProject) throws SQLException {
        String sql = " INSERT INTO projectmaterialdetails (ProjectID, MaterialID, Qty) VALUES (? ,? ,? ) ";
        return CrudUtil.execute(sql, materialProject.getProjectId(), materialProject.getMaterialId(),materialProject.getQty());
    }

    public boolean delete(String projectId) throws SQLException {
        String sql = "DELETE FROM projectmaterialdetails WHERE ProjectID = ? ";
        return CrudUtil.execute(sql, projectId);
    }

    public boolean update(ProjectMaterials materialProject) throws SQLException {
        String sql = "UPDATE projectmaterialdetails SET MaterialID = ?, Qty = ? WHERE ProjectID = ? ";
        return CrudUtil.execute(sql, materialProject.getMaterialId(), materialProject.getQty(), materialProject.getProjectId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<ProjectMaterials> searchMaterialProject(String projectId) throws SQLException {
        String sql = "SELECT * FROM projectmaterialdetails WHERE ProjectID = ?";
        ResultSet rst = CrudUtil.execute(sql, projectId);
        ArrayList<ProjectMaterials> projectMaterialsDtos = new ArrayList<>();

        while (rst.next()) {
            ProjectMaterials projectMaterialsDto = new ProjectMaterials(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            projectMaterialsDtos.add(projectMaterialsDto);
        }
        return projectMaterialsDtos;
    }
    public ArrayList<ProjectMaterials> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM projectmaterialdetails");
        ArrayList<ProjectMaterials> projectMaterialsDtos = new ArrayList<>();

        while (rst.next()) {
            ProjectMaterials projectMaterials = new ProjectMaterials(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            projectMaterialsDtos.add(projectMaterials);
        }
        return projectMaterialsDtos;
    }
    public boolean saveProjectMaterials(AddProjectWantedCustom addProjectWantedDto) throws SQLException {
        for (ProjectMaterials dto : addProjectWantedDto.getProjectMaterialsDtos()) {
            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO projectmaterialdetails VALUES (?,?,?)",
                    addProjectWantedDto.getProjectId(),
                    dto.getMaterialId(),
                    dto.getQty()
            );

            if (!isSaved) return false;
        }
        return true;
    }
}
