package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProjectFormBO extends SuperBO {

     String getNextProjectId() throws SQLException, ClassNotFoundException;
     ArrayList<ProjectDto> getAllProject() throws SQLException, ClassNotFoundException;
//    public boolean saveProject(ProjectDto projectDto) throws SQLException {
//        String sql = "INSERT INTO project (ProjectID, Name, Description, CustomerID, StartDate, EndDate, UserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        return CrudUtil.execute(sql, projectDto.getProjectId(), projectDto.getProjectName(), projectDto.getProjectDescription(), projectDto.getCustomerId(), projectDto.getStartDate() , projectDto .getEndDate(), projectDto .getUserId());
//    }
     boolean deleteProject(String projectId) throws SQLException, ClassNotFoundException;
     boolean updateProject(ProjectDto projectDto) throws SQLException, ClassNotFoundException;
     ProjectDto searchProject(String projectId) throws SQLException, ClassNotFoundException;
     boolean isProjectIdUsed(String projectId) throws SQLException ;
}
