package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.MachineProject;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.custom.AddProjectWantedCustom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineProjectDAO extends CrudDAO<MachineProject> {
//    String getNextProjectID() throws SQLException;
//    boolean saveMachineProject(MachineProjectDto machineProjectDto) throws SQLException;
//    boolean deleteMachineProject(String projectId) throws SQLException;
//    boolean updateMachineProject(MachineProjectDto machineProjectDto) throws SQLException;
     ArrayList<MachineProject> searchMachineProject(String projectId) throws SQLException;
//    ArrayList<MachineProjectDto> getAllMachineProjectDetails() throws SQLException;
     boolean saveProjectMachines(AddProjectWantedCustom addProjectWantedDto) throws SQLException;
}
