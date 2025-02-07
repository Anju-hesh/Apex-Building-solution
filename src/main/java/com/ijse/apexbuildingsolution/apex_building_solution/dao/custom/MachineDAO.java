package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Machine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineDAO extends CrudDAO<Machine> {
//    String getNextMachineId() throws SQLException;
//    ArrayList<MachineDto> getAllMachinne() throws SQLException;
//    boolean saveMachine(MachineDto machineDto) throws SQLException;
//    boolean deleteMachine(String machineId) throws SQLException;
//    boolean updateMachine(MachineDto machineDto) throws SQLException;
//    MachineDto searchMachine(String machineId) throws SQLException;
    Machine findById(String selectedMachineId) throws SQLException;
    ArrayList<String> getAllMachineIds() throws SQLException;
}
