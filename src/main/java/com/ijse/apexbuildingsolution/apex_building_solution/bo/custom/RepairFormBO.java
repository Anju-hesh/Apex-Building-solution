package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.RepairDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairFormBO extends SuperBO {

     String getNextRepairId() throws SQLException, ClassNotFoundException;
     ArrayList<RepairDto> getAllRepairs() throws SQLException, ClassNotFoundException;
     boolean saveRepair(RepairDto repairDto) throws SQLException, ClassNotFoundException;
     boolean deleteRepair(String repairId) throws SQLException, ClassNotFoundException;
     boolean updateRepair(RepairDto repairDto) throws SQLException, ClassNotFoundException;
     RepairDto searchRepair(String machineId) throws SQLException, ClassNotFoundException;
     boolean updateMachineQuantity(RepairDto repair, int qtyDuplicate) throws SQLException ;
}
