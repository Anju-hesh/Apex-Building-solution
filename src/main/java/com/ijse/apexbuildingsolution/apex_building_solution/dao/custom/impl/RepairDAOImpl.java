package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.RepairDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.db.DBConnection;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.RepairDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Repair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairDAOImpl implements RepairDAO {
    public String getNextId() throws SQLException {

        String sql = "SELECT RepairID FROM repair ORDER BY RepairID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String repairId = resultSet.getString(1);
            String subrepairId = repairId.substring(4);
            int lastIdIndex = Integer.parseInt(subrepairId);
            int nextIndex = lastIdIndex + 1;
            String newId = String.format("REP%03d", nextIndex);
            return newId;
        }
        return "REP001";
    }
    public ArrayList<Repair> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM repair");
        ArrayList<Repair> repairDtos = new ArrayList<>();

        while (rst.next()) {
            Repair repair = new Repair(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            repairDtos.add(repair);
        }
        return repairDtos;
    }

    public boolean save(Repair repair) throws SQLException {
        String sql = "INSERT INTO repair (RepairID, MachineID, Qty) VALUES (?, ?, ?) ";
        return CrudUtil.execute(sql, repair.getRepairId(), repair.getMachineId(), repair.getQty());
    }

    public boolean delete(String repairId) throws SQLException {
        String sql = "DELETE FROM repair WHERE RepairID = ?";
        return CrudUtil.execute(sql, repairId);
    }

    public boolean update(Repair repair) throws SQLException {
        String sql = "UPDATE repair SET  MachineID = ?, Qty = ? WHERE RepairID = ?";
        return CrudUtil.execute(sql, repair.getMachineId() ,  repair.getQty(), repair.getRepairId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Repair search(String machineId) throws SQLException {
        String sql = "SELECT * FROM repair WHERE MachineID = ?";
        ResultSet rst = CrudUtil.execute(sql, machineId);
        if (rst.next()) {
            return new Repair(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
        }
        return null;
    }

    public boolean updateMachineQuantity(Repair repair, int qtyDuplicate) throws SQLException {
        Connection connection = DBConnection.getInstance().getCONNECTION();
        PreparedStatement preparedStatement;

        try {
            connection.setAutoCommit(false);

            if (repair.getQty() < qtyDuplicate) {
                String updateQtySqlIncrease = "UPDATE machine SET QtyOnHand = QtyOnHand + ? WHERE MachineID = ?";
                preparedStatement = connection.prepareStatement(updateQtySqlIncrease);
                preparedStatement.setInt(1, qtyDuplicate - repair.getQty()); // Adding the difference
                preparedStatement.setString(2, repair.getMachineId());
                preparedStatement.executeUpdate();
            } else {
                String updateQtySqlDecrease = "UPDATE machine SET QtyOnHand = QtyOnHand - ? WHERE MachineID = ?";
                preparedStatement = connection.prepareStatement(updateQtySqlDecrease);
                preparedStatement.setInt(1, repair.getQty()); // Use the actual repair quantity
                preparedStatement.setString(2, repair.getMachineId());
                preparedStatement.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
