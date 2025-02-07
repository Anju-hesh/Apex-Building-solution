package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.RepairDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.RepairFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl.RepairDAOImpl;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.RepairDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairFormBOImpl implements RepairFormBO {

    RepairDAO repairDAO = (RepairDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REPAIR);

    public String getNextRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.getNextId();
    }
    public ArrayList<RepairDto> getAllRepairs() throws SQLException, ClassNotFoundException {
//        return repairDAO.getAll();
        ArrayList<RepairDto> repairDtos = new ArrayList<>();
        ArrayList<Repair> repairs = repairDAO.getAll();

        for (Repair repair : repairs) {
            repairDtos.add(new RepairDto(
                    repair.getRepairId(),
                    repair.getMachineId(),
                    repair.getQty()
            ));
        }
        return repairDtos;
    }

    public boolean saveRepair(RepairDto repairDto) throws SQLException, ClassNotFoundException {
//        return repairDAO.save(repairDto);
        Repair repair = new Repair(
                repairDto.getRepairId(),
                repairDto.getMachineId(),
                repairDto.getQty()
        );
        return repairDAO.save(repair);
    }

    public boolean deleteRepair(String repairId) throws SQLException, ClassNotFoundException {
        return repairDAO.delete(repairId);
    }

    public boolean updateRepair(RepairDto repairDto) throws SQLException, ClassNotFoundException {
//        return repairDAO.update(repairDto);

        Repair repair = new Repair(
                repairDto.getRepairId(),
                repairDto.getMachineId(),
                repairDto.getQty()
        );
        return repairDAO.update(repair);
    }

    public RepairDto searchRepair(String machineId) throws SQLException, ClassNotFoundException {
//        return repairDAO.search(machineId);

        Repair repair = repairDAO.search(machineId);
        if (repair != null) {
            return new RepairDto(
                    repair.getRepairId(),
                    repair.getMachineId(),
                    repair.getQty()
            );
        }
        return null;
    }

    public boolean updateMachineQuantity(RepairDto repair, int qtyDuplicate) throws SQLException {
//        Connection connection = DBConnection.getInstance().getCONNECTION();
//        PreparedStatement preparedStatement;
//
//        try {
//            connection.setAutoCommit(false);
//
//            if (repair.getQty() < qtyDuplicate) {
//                String updateQtySqlIncrease = "UPDATE machine SET QtyOnHand = QtyOnHand + ? WHERE MachineID = ?";
//                preparedStatement = connection.prepareStatement(updateQtySqlIncrease);
//                preparedStatement.setInt(1, qtyDuplicate - repair.getQty()); // Adding the difference
//                preparedStatement.setString(2, repair.getMachineId());
//                preparedStatement.executeUpdate();
//            } else {
//                String updateQtySqlDecrease = "UPDATE machine SET QtyOnHand = QtyOnHand - ? WHERE MachineID = ?";
//                preparedStatement = connection.prepareStatement(updateQtySqlDecrease);
//                preparedStatement.setInt(1, repair.getQty()); // Use the actual repair quantity
//                preparedStatement.setString(2, repair.getMachineId());
//                preparedStatement.executeUpdate();
//            }
//
//            connection.commit();
//            return true;
//
//        } catch (SQLException e) {
//            connection.rollback();
//            e.printStackTrace();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//        return repairDAO.updateMachineQuantity(repair, qtyDuplicate);
        try {
            String sql;
            int qtyDifference = Math.abs(qtyDuplicate - repair.getQty());

            if (repair.getQty() < qtyDuplicate) {
                // Increase machine quantity
                sql = "UPDATE machine SET QtyOnHand = QtyOnHand + ? WHERE MachineID = ?";
            } else {
                // Decrease machine quantity
                sql = "UPDATE machine SET QtyOnHand = QtyOnHand - ? WHERE MachineID = ?";
            }

            return CrudUtil.execute(sql, qtyDifference, repair.getMachineId());

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Ensure exception propagates for handling at a higher level
        }
    }
}
