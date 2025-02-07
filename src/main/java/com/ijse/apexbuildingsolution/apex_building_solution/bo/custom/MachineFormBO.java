package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineFormBO extends SuperBO {
     String getNextMachineId() throws SQLException, ClassNotFoundException;
     ArrayList<MachineDto> getAllMachinne() throws SQLException, ClassNotFoundException;
     boolean saveMachine(MachineDto machineDto) throws SQLException, ClassNotFoundException;
     boolean deleteMachine(String machineId) throws SQLException, ClassNotFoundException;
     boolean updateMachine(MachineDto machineDto) throws SQLException, ClassNotFoundException;
     MachineDto searchMachine(String machineId) throws SQLException, ClassNotFoundException;
     MachineDto findById(String selectedMachineId) throws SQLException ;
     ArrayList<String> getAllMachineIds() throws SQLException ;
}
