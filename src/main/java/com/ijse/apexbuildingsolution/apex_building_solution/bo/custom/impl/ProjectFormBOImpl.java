package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.ProjectFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.ProjectDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl.ProjectDAOImpl;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.PaymentDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Payment;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Project;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectFormBOImpl implements ProjectFormBO {

    ProjectDAO projectDAO = (ProjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROJECT);

    public String getNextProjectId() throws SQLException, ClassNotFoundException {
        return projectDAO.getNextId();
    }
    public ArrayList<ProjectDto> getAllProject() throws SQLException, ClassNotFoundException {
//        return projectDAO.getAll();
        ArrayList<ProjectDto> projectDtos = new ArrayList<>();
        ArrayList<Project> projects = projectDAO.getAll();
        for (Project project : projects) {
            projectDtos.add(new ProjectDto(project.getProjectId(),project.getProjectName(),project.getProjectDescription(),project.getCustomerId(),project.getStartDate(),project.getEndDate(),project.getUserId()));
        }
        return projectDtos;
    }

//    public boolean saveProject(ProjectDto projectDto) throws SQLException {
//        String sql = "INSERT INTO project (ProjectID, Name, Description, CustomerID, StartDate, EndDate, UserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        return CrudUtil.execute(sql, projectDto.getProjectId(), projectDto.getProjectName(), projectDto.getProjectDescription(), projectDto.getCustomerId(), projectDto.getStartDate() , projectDto .getEndDate(), projectDto .getUserId());
//    }

    public boolean deleteProject(String projectId) throws SQLException, ClassNotFoundException {
        return projectDAO.delete(projectId);
    }

    public boolean updateProject(ProjectDto projectDto) throws SQLException, ClassNotFoundException {
        return projectDAO.update(new Project(projectDto.getProjectId(),projectDto.getProjectName(),projectDto.getProjectDescription(),projectDto.getCustomerId(),projectDto.getStartDate(),projectDto.getEndDate(),projectDto.getUserId()));
    }

    public ProjectDto searchProject(String projectId) throws SQLException, ClassNotFoundException {
//        return projectDAO.search(projectId);
        ArrayList<ProjectDto> projectDtos = new ArrayList<>();

        Project project = projectDAO.search(projectId);
        ProjectDto projectDto = new ProjectDto(project.getProjectId(),project.getProjectName(),project.getProjectDescription(),project.getCustomerId(),project.getStartDate(),project.getEndDate(),project.getUserId());
        projectDtos.add(projectDto);
        return projectDtos.get(0);
    }

    public boolean isProjectIdUsed(String projectId) throws SQLException {
        return projectDAO.isProjectIdUsed(projectId);
    }
}
