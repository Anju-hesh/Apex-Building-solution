package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.AddProjectWantedFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.*;
import com.ijse.apexbuildingsolution.apex_building_solution.db.DBConnection;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.PaymentDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.ProjectMaterialsDto;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddProjectFormBoImpl implements AddProjectWantedFormBO {

    MachineDAO machineDAO = (MachineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MACHINE);
    MachineProjectDAO machineProjectDAO = (MachineProjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MACHINEPROJECT);
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    ProjectDAO projectDAO = (ProjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROJECT);
    ProjectMaterialDAO projectMaterialDAO = (ProjectMaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROJECTMATERIAL);


//    AddProjectWantedDAO addProjectWantedDAO = (AddProjectWantedDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADDPROJECTWANTED);

//    public boolean saveProject(AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        return addProjectWantedDAO.saveProject(addProjectWantedDto);
//    }
//    public boolean saveProject(AddProjectWantedDto addProjectWantedDto) throws SQLException {
//
//        Connection connection = DBConnection.getInstance().getCONNECTION();
//
//        try {
//
//            connection.setAutoCommit(false);
//
//            boolean isProjectSaved = CrudUtil.execute(
//                "INSERT INTO project VALUES (?,?,?,?,?,?,?)",
//                addProjectWantedDto.getProjectId(),
//                addProjectWantedDto.getProjectName(),
//                addProjectWantedDto.getProjectDescription(),
//                addProjectWantedDto.getCustomerId(),
//                addProjectWantedDto.getProjectStartDate(),
//                addProjectWantedDto.getProjectEndDate(),
//                addProjectWantedDto.getUserId()
//            );
//            if (isProjectSaved) {
//
//                if (saveProjectMaterials(connection, addProjectWantedDto) &&
//                    updateMaterialQuantities(connection, addProjectWantedDto) &&
//                    saveProjectMachines(connection, addProjectWantedDto) &&
//                    updateMachineQuantities(connection, addProjectWantedDto) &&
//                    savePayments(connection, addProjectWantedDto)) {
//
//                        connection.commit();
//                        new Alert(Alert.AlertType.INFORMATION, "Successfully Saved Project!").show();
//                        return true;
//                } else {
//                    connection.rollback();
//                    new Alert(Alert.AlertType.ERROR, "Error in Saving Project Details!").show();
//                    return false;
//                }
//            } else {
//                connection.rollback();
//                new Alert(Alert.AlertType.ERROR, "Error in Saving Project!").show();
//                return false;
//            }
//        } catch (Exception e) {
//            connection.rollback();
//            new Alert(Alert.AlertType.ERROR, "Transaction Failed: " + e.getMessage()).show();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
//
//    private boolean saveProjectMaterials(Connection connection, AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        String sql = "INSERT INTO projectmaterialdetails VALUES (?,?,?)";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//
//            for (ProjectMaterialsDto dto : addProjectWantedDto.getProjectMaterialsDtos()) {
//                ps.setString(1, addProjectWantedDto.getProjectId());
//                ps.setString(2, dto.getMaterialId());
//                ps.setInt(3, dto.getQty());
//
//                if (ps.executeUpdate() <= 0) return false;
//            }
//            return true;
//
//        }catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Save Project Material Error: " + e.getMessage()).show();
//            return false;
//        }
//    }
//    private boolean updateMaterialQuantities(Connection connection, AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        String sql = "UPDATE material SET Qty_On_Hand = Qty_On_Hand - ? WHERE MaterialID = ?";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//
//            for (ProjectMaterialsDto dto : addProjectWantedDto.getProjectMaterialsDtos()) {
//                ps.setInt(1, dto.getQty());
//                ps.setString(2, dto.getMaterialId());
//
//                if (ps.executeUpdate() <= 0) return false;
//            }
//            return true;
//
//        }catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Update Project Material Quantity Error: " + e.getMessage()).show();
//            return false;
//        }
//    }
//
//    private boolean saveProjectMachines(Connection connection, AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        String sql = "INSERT INTO projectmachinedetails VALUES (?,?,?)";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//
//            for (MachineProjectDto dto : addProjectWantedDto.getMachineProjectDtos()) {
//                ps.setString(1, addProjectWantedDto.getProjectId());
//                ps.setString(2, dto.getMachineId());
//                ps.setInt(3, dto.getQty());
//
//                if (ps.executeUpdate() <= 0) return false;
//            }
//            return true;
//
//        }catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Save Project Machines Error: " + e.getMessage()).show();
//            return false;
//        }
//    }
//
//    private boolean updateMachineQuantities(Connection connection, AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        String updateQtySql = "UPDATE machine SET QtyOnHand = QtyOnHand - ? WHERE MachineID = ?";
//        String updateAvailabilitySql = "UPDATE machine SET Availability = ? WHERE MachineID = ?";
//
//        try {
//            PreparedStatement qtyPs = connection.prepareStatement(updateQtySql);
//            PreparedStatement availabilityPs = connection.prepareStatement(updateAvailabilitySql);
//
//            for (MachineProjectDto dto : addProjectWantedDto.getMachineProjectDtos()) {
//
//                qtyPs.setInt(1, dto.getQty());
//                qtyPs.setString(2, dto.getMachineId());
//
//                if (qtyPs.executeUpdate() <= 0) return false;
//
//                boolean availability = checkQtyZero(connection, dto.getMachineId());
//                availabilityPs.setBoolean(1, availability);
//                availabilityPs.setString(2, dto.getMachineId());
//
//                if (availabilityPs.executeUpdate() <= 0) return false;
//            }
//            return true;
//
//        }catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Update Project Machines Quantity Error: " + e.getMessage()).show();
//            return false;
//        }
//    }
//
//    private boolean checkQtyZero(Connection connection, String machineId) throws SQLException {
//        String sql = "SELECT QtyOnHand FROM machine WHERE MachineID = ?";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//
//            ps.setString(1, machineId);
//            var rs = ps.executeQuery();
//
//            if (rs.next()) {
//                return rs.getInt("QtyOnHand") > 0; // Availability should be true if QtyOnHand is above zero
//            }
//            return false;
//        }catch (SQLException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Check Quantity Error: " + e.getMessage()).show();
//            return false;
//        }
//    }
//
//    private boolean savePayments(Connection connection, AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        String sql = "INSERT INTO payment VALUES (?,?,?,?,?,?)";
//
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            for (PaymentDto dto : addProjectWantedDto.getPaymentDtos()) {
//                ps.setString(1, dto.getPaymentId());
//                ps.setString(2, dto.getPaymentMethod());
//                ps.setDouble(3, dto.getFullBalance());
//                ps.setDouble(4, dto.getPayedBalance());
//                ps.setString(5, dto.getProjectId());
//                ps.setString(6, dto.getStatus());
//
//                if (ps.executeUpdate() <= 0) return false;
//            }
//            return true;
//
//        }catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Save Payment Error: " + e.getMessage()).show();
//            return false;
//        }
//    }

    public boolean saveProject(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getCONNECTION();

        try {
            connection.setAutoCommit(false);

            boolean isProjectSaved = CrudUtil.execute(
                    "INSERT INTO project VALUES (?,?,?,?,?,?,?)",
                    addProjectWantedDto.getProjectId(),
                    addProjectWantedDto.getProjectName(),
                    addProjectWantedDto.getProjectDescription(),
                    addProjectWantedDto.getCustomerId(),
                    addProjectWantedDto.getProjectStartDate(),
                    addProjectWantedDto.getProjectEndDate(),
                    addProjectWantedDto.getUserId()
            );

            if (isProjectSaved &&
                    saveProjectMaterials(addProjectWantedDto) &&
                    updateMaterialQuantities(addProjectWantedDto) &&
                    saveProjectMachines(addProjectWantedDto) &&
                    updateMachineQuantities(addProjectWantedDto) &&
                    savePayments(addProjectWantedDto)) {

                connection.commit();
                new Alert(Alert.AlertType.INFORMATION, "Successfully Saved Project!").show();
                return true;
            } else {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Error in Saving Project Details!").show();
                return false;
            }
        } catch (Exception e) {
            connection.rollback();
            new Alert(Alert.AlertType.ERROR, "Transaction Failed: " + e.getMessage()).show();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private boolean saveProjectMaterials(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        for (ProjectMaterialsDto dto : addProjectWantedDto.getProjectMaterialsDtos()) {
            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO projectmaterialdetails VALUES (?,?,?)",
                    addProjectWantedDto.getProjectId(),
                    dto.getMaterialId(),
                    dto.getQty()
            );

            if (!isSaved) return false;
        }
        return true;
    }

    private boolean updateMaterialQuantities(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        for (ProjectMaterialsDto dto : addProjectWantedDto.getProjectMaterialsDtos()) {
            boolean isUpdated = CrudUtil.execute(
                    "UPDATE material SET Qty_On_Hand = Qty_On_Hand - ? WHERE MaterialID = ?",
                    dto.getQty(),
                    dto.getMaterialId()
            );

            if (!isUpdated) return false;
        }
        return true;
    }

    private boolean saveProjectMachines(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        for (MachineProjectDto dto : addProjectWantedDto.getMachineProjectDtos()) {
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

    private boolean updateMachineQuantities(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        for (MachineProjectDto dto : addProjectWantedDto.getMachineProjectDtos()) {
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

    private boolean savePayments(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        for (PaymentDto dto : addProjectWantedDto.getPaymentDtos()) {
            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO payment VALUES (?,?,?,?,?,?)",
                    dto.getPaymentId(),
                    dto.getPaymentMethod(),
                    dto.getFullBalance(),
                    dto.getPayedBalance(),
                    dto.getProjectId(),
                    dto.getStatus()
            );

            if (!isSaved) return false;
        }
        return true;
    }
}
