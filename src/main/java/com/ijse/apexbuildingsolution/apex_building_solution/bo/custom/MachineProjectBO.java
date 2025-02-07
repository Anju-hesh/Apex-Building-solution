package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineProjectDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineProjectBO extends SuperBO {

     String getNextProjectID() throws SQLException, ClassNotFoundException;
     boolean saveMachineProject(MachineProjectDto machineProjectDto) throws SQLException, ClassNotFoundException;
     boolean deleteMachineProject(String projectId) throws SQLException, ClassNotFoundException;
     boolean updateMachineProject(MachineProjectDto machineProjectDto) throws SQLException, ClassNotFoundException;
     ArrayList<MachineProjectDto> searchMachineProject(String projectId) throws SQLException, ClassNotFoundException;
     ArrayList<MachineProjectDto> getAllMachineProjectDetails() throws SQLException, ClassNotFoundException;
}
