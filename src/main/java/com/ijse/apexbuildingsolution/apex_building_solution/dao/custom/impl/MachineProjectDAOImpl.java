package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.MachineProjectDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.MachineProject;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.custom.AddProjectWantedCustom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineProjectDAOImpl implements MachineProjectDAO {
    public String getNextId() throws SQLException {
        String sql = "SELECT ProjectID FROM projectmachinedetails ORDER BY ProjectID DESC LIMIT 1";

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
    public boolean save(MachineProject machineProject) throws SQLException {
        String sql = " INSERT INTO projectmachinedetails (ProjectID, MachineID, Qty) VALUES (? ,? ,? ) ";
        return CrudUtil.execute(sql, machineProject.getProjectId(), machineProject.getMachineId(),machineProject.getQty());
    }

    public boolean delete(String projectId) throws SQLException {
        String sql = "DELETE FROM projectmachinedetails WHERE ProjectID = ? ";
        return CrudUtil.execute(sql, projectId);
    }

    @Override
    public MachineProject search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(MachineProject machineProject) throws SQLException {
        String sql = "UPDATE projectmachinedetails SET MachineID = ?, Qty = ? WHERE ProjectID = ? ";
        return CrudUtil.execute(sql, machineProject.getMachineId(), machineProject.getQty(), machineProject.getProjectId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<MachineProject> searchMachineProject(String projectId) throws SQLException {
        String sql = "SELECT * FROM projectmachinedetails WHERE ProjectID = ?";
        ResultSet rst = CrudUtil.execute(sql, projectId);
        ArrayList<MachineProject> machineProjectDtos = new ArrayList<>();

        while (rst.next()) {
            MachineProject machineProject = new MachineProject(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            machineProjectDtos.add(machineProject);
        }
        return machineProjectDtos;
    }
    public ArrayList<MachineProject> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM projectmachinedetails");
        ArrayList<MachineProject> machineProjectDtos = new ArrayList<>();

        while (rst.next()) {
            MachineProject machineProject = new MachineProject(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            machineProjectDtos.add(machineProject);
        }
        return machineProjectDtos;
    }
    public boolean saveProjectMachines(AddProjectWantedCustom addProjectWantedDto) throws SQLException {
        for (MachineProject dto : addProjectWantedDto.getMachineProjectDtos()) {
            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO projectmachinedetails VALUES (?,?,?)",
                    addProjectWantedDto.getProjectId(),
                    dto.getMachineId(),
                    dto.getQty()
            );

            if (!isSaved) return false;
        }
        return true;
    }
}
