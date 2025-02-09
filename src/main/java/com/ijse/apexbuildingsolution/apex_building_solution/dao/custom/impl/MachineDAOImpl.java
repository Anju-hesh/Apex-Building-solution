package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.MachineDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Machine;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.MachineProject;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.custom.AddProjectWantedCustom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineDAOImpl implements MachineDAO {

    public String getNextId() throws SQLException {
        String sql = "SELECT MachineID FROM machine ORDER BY MachineID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String userId = resultSet.getString(1); // USER001
            String subuserId = userId.substring(4); // 001
            int lastIdIndex = Integer.parseInt(subuserId); // 1
            int nextIndex = lastIdIndex + 1; // 2
            String newId = String.format("MACH%03d", nextIndex); // USER002
            return newId;
        }
        return "MACH001";
    }
    public ArrayList<Machine> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM machine");
        ArrayList<Machine> machineDtos = new ArrayList<>();

        while (rst.next()) {
            Machine machine = new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBoolean(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
            machineDtos.add(machine);
        }
        return machineDtos;
    }
    public boolean save(Machine machine) throws SQLException {
        String sql = "INSERT INTO machine (MachineID, Name, Availability, Status , QtyOnHand) VALUES ( ?, ?, ?, ?, ? )";
        return CrudUtil.execute(sql, machine.getMachineId(), machine.getMachineName(), machine.isAvailability(), machine.getStatus(), machine.getQtyOnHand());
    }

    public boolean delete(String machineId) throws SQLException {
        String sql = "DELETE FROM machine WHERE MachineID = ?";
        return CrudUtil.execute(sql, machineId);
    }

    public boolean update(Machine machine) throws SQLException {
        String sql = "UPDATE machine SET Name = ?, Availability = ?, Status = ? , QtyOnHand = ? WHERE MachineID = ?";
        return CrudUtil.execute(sql, machine.getMachineName(), machine.isAvailability(), machine.getStatus(), machine.getQtyOnHand(), machine.getMachineId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Machine search(String machineId) throws SQLException {
        String sql = "SELECT * FROM machine WHERE MachineID = ?";
        ResultSet rst = CrudUtil.execute(sql, machineId);
        if (rst.next()) {
            return new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBoolean(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
        }
        return null;
    }
    public Machine findById(String selectedMachineId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM machine WHERE Name = ?", selectedMachineId);

        if (rst.next()) {
            return new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBoolean(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
        }
        return null;
    }
    public ArrayList<String> getAllMachineIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT Name FROM machine");

        ArrayList<String> machineIds = new ArrayList<>();

        while (rst.next()) {
            machineIds.add(rst.getString(1));
        }
        return machineIds;
    }
    public boolean updateMachineQuantities(AddProjectWantedCustom addProjectWantedDto) throws SQLException {
        for (MachineProject dto : addProjectWantedDto.getMachineProjectDtos()) {
            boolean isUpdated = CrudUtil.execute(
                    "UPDATE machine SET QtyOnHand = QtyOnHand - ? WHERE MachineID = ?",
                    dto.getQty(),
                    dto.getMachineId()
            );

            if (!isUpdated) return false;

            boolean availability = checkQtyZero(dto.getMachineId());
            boolean isAvailabilityUpdated = CrudUtil.execute(
                    "UPDATE machine SET Availability = ? WHERE MachineID = ?",
                    availability,
                    dto.getMachineId()
            );

            if (!isAvailabilityUpdated) return false;
        }
        return true;
    }
    private boolean checkQtyZero(String machineId) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT QtyOnHand FROM machine WHERE MachineID = ?",
                machineId
        );

        if (rs.next()) {
            return rs.getInt("QtyOnHand") > 0; // Availability should be true if QtyOnHand is above zero
        }
        return false;
    }
}
