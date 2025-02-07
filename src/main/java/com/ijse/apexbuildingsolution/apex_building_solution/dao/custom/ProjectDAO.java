package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Project;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProjectDAO extends CrudDAO<Project> {

//    String getNextProjectId() throws SQLException;
//    ArrayList<ProjectDto> getAllProject() throws SQLException;
//    boolean deleteProject(String projectId) throws SQLException;
//    boolean updateProject(ProjectDto projectDto) throws SQLException;
//    ProjectDto searchProject(String projectId) throws SQLException;
//  boolean save(Project projectDto) throws SQLException ;
    boolean isProjectIdUsed(String projectId) throws SQLException;
}
