package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.ProjectDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDAOImpl implements ProjectDAO {

    public String getNextId() throws SQLException {
        String sql = "SELECT ProjectID FROM project ORDER BY ProjectID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String ProjectID = resultSet.getString(1); // USER001
            String subProjectID = ProjectID.substring(4); // 001
            int lastIdIndex = Integer.parseInt(subProjectID); // 1
            int nextIndex = lastIdIndex + 1; // 2
            String newId = String.format("PROJ%03d", nextIndex); // USER002
            return newId;
        }
        return "PROJ001";
    }
    public ArrayList<Project> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM project");
        ArrayList<Project> projectDTOS = new ArrayList<>();

        while (rst.next()) {
            Project project = new Project(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getDate(6),
                    rst.getString(7)
            );
            projectDTOS.add(project);
        }
        return projectDTOS;
    }

    @Override
    public boolean save(Project dto) throws SQLException, ClassNotFoundException {
        return false;
    }
//    AddProjectWantedDto addProjectWantedDto = new AddProjectWantedDto();

//    @Override
//    public boolean save(Project dto) throws SQLException {
//        boolean isProjectSaved = CrudUtil.execute(
//                    "INSERT INTO project VALUES (?,?,?,?,?,?,?)",
//                addProjectWantedDto.getProjectId(),
//                addProjectWantedDto.getProjectName(),
//                addProjectWantedDto.getProjectDescription(),
//                addProjectWantedDto.getCustomerId(),
//                addProjectWantedDto.getProjectStartDate(),
//                addProjectWantedDto.getProjectEndDate(),
//                addProjectWantedDto.getUserId()
//            );
//        return isProjectSaved;
//    }

    public boolean saveProject(ProjectDto projectDto) throws SQLException {
        String sql = "INSERT INTO project (ProjectID, Name, Description, CustomerID, StartDate, EndDate, UserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, projectDto.getProjectId(), projectDto.getProjectName(), projectDto.getProjectDescription(), projectDto.getCustomerId(), projectDto.getStartDate() , projectDto .getEndDate(), projectDto .getUserId());
    }

    public boolean delete(String projectId) throws SQLException {
        String sql = "DELETE FROM project WHERE ProjectID = ?";
        return CrudUtil.execute(sql, projectId);
    }

    public boolean update(Project project) throws SQLException {
        String sql = "UPDATE project SET Name = ?, Description = ?, CustomerID = ? , StartDate = ? , EndDate = ? , UserID = ? WHERE ProjectID = ?";
        return CrudUtil.execute(sql, project.getProjectName(), project.getProjectDescription(), project.getCustomerId(), project.getStartDate() , project .getEndDate() , project .getUserId(), project.getProjectId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Project search(String projectId) throws SQLException {
        String sql = "SELECT * FROM project WHERE ProjectID = ? ";
        ResultSet rst = CrudUtil.execute(sql, projectId);
        if (rst.next()) {
            return new Project(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getDate(6),
                    //  rst.getString(7),
                    rst.getString(7)
            );
        }
        return null;
    }

    public boolean isProjectIdUsed(String projectId) throws SQLException {
        String query = "SELECT COUNT(*) FROM project WHERE ProjectID = ?";

        ResultSet resultSet = CrudUtil.execute(query, projectId);

        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0;
        }

        return false;
    }
}
