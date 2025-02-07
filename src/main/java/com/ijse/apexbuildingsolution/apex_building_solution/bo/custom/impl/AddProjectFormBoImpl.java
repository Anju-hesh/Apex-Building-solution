package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.AddProjectWantedFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.*;
import com.ijse.apexbuildingsolution.apex_building_solution.db.DBConnection;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;

public class AddProjectFormBoImpl implements AddProjectWantedFormBO {

    MachineDAO machineDAO = (MachineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MACHINE);
    MachineProjectDAO machineProjectDAO = (MachineProjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MACHINEPROJECT);
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    ProjectDAO projectDAO = (ProjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROJECT);
    ProjectMaterialDAO projectMaterialDAO = (ProjectMaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROJECTMATERIAL);

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
        return projectMaterialDAO.saveProjectMaterials(addProjectWantedDto);
    }

    private boolean updateMaterialQuantities(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        return materialDAO.updateMaterialQuantities(addProjectWantedDto);
    }

    private boolean saveProjectMachines(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        return machineProjectDAO.saveProjectMachines(addProjectWantedDto);
    }

    private boolean updateMachineQuantities(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        return machineDAO.updateMachineQuantities(addProjectWantedDto);
    }

    private boolean savePayments(AddProjectWantedDto addProjectWantedDto) throws SQLException {
        return paymentDAO.savePayments(addProjectWantedDto);
    }
}
