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
import com.ijse.apexbuildingsolution.apex_building_solution.entity.MachineProject;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Payment;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.ProjectMaterials;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.custom.AddProjectWantedCustom;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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

//        return projectMaterialDAO.saveProjectMaterials(addProjectWantedDto);
        return projectMaterialDAO.saveProjectMaterials(new AddProjectWantedCustom(addProjectWantedDto.getProjectId(),
                addProjectWantedDto.getProjectName(),addProjectWantedDto.getProjectDescription(),
                addProjectWantedDto.getCustomerId(),addProjectWantedDto.getProjectStartDate(),
                addProjectWantedDto.getProjectEndDate(),addProjectWantedDto.getUserId(),
                convertToProjectMaterialsEntities(addProjectWantedDto.getProjectMaterialsDtos()),
                convertToMachineProjectEntities(addProjectWantedDto.getMachineProjectDtos()),
                convertToPaymentEntities(addProjectWantedDto.getPaymentDtos())
        ));
    }
    private ArrayList<ProjectMaterials> convertToProjectMaterialsEntities(ArrayList<ProjectMaterialsDto> dtos) {
        ArrayList<ProjectMaterials> entities = new ArrayList<>();
        if (dtos != null) {
            for (ProjectMaterialsDto dto : dtos) {
                ProjectMaterials entity = new ProjectMaterials();
                entity.setProjectId(dto.getProjectId());
                entity.setMaterialId(dto.getMaterialId());
                entity.setQty(dto.getQty());
                entities.add(entity);
            }
        }
        return entities;
    }

    private ArrayList<MachineProject> convertToMachineProjectEntities(ArrayList<MachineProjectDto> dtos) {
        ArrayList<MachineProject> entities = new ArrayList<>();
        if (dtos != null) {
            for (MachineProjectDto dto : dtos) {
                MachineProject entity = new MachineProject();
                entity.setProjectId(dto.getProjectId());
                entity.setMachineId(dto.getMachineId());
                entity.setQty(dto.getQty());
                entities.add(entity);
            }
        }
        return entities;
    }

    private ArrayList<Payment> convertToPaymentEntities(ArrayList<PaymentDto> dtos) {
        ArrayList<Payment> entities = new ArrayList<>();
        if (dtos != null) {
            for (PaymentDto dto : dtos) {
                Payment entity = new Payment();
                entity.setPaymentId(dto.getPaymentId());
                entity.setPaymentMethod(dto.getPaymentMethod());
                entity.setFullBalance(dto.getFullBalance());
                entity.setPayedBalance(dto.getPayedBalance());
                entity.setProjectId(dto.getProjectId());
                entity.setStatus(dto.getStatus());
                entities.add(entity);
            }
        }
        return entities;
    }


    private boolean updateMaterialQuantities(AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        return materialDAO.updateMaterialQuantities(addProjectWantedDto);
        return materialDAO.updateMaterialQuantities(new AddProjectWantedCustom(addProjectWantedDto.getProjectId(),
                addProjectWantedDto.getProjectName(),addProjectWantedDto.getProjectDescription(),
                addProjectWantedDto.getCustomerId(),addProjectWantedDto.getProjectStartDate(),
                addProjectWantedDto.getProjectEndDate(),addProjectWantedDto.getUserId(),
                convertToProjectMaterialsEntities(addProjectWantedDto.getProjectMaterialsDtos()),
                convertToMachineProjectEntities(addProjectWantedDto.getMachineProjectDtos()),
                convertToPaymentEntities(addProjectWantedDto.getPaymentDtos())
        ));
    }

    private boolean saveProjectMachines(AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        return machineProjectDAO.saveProjectMachines(addProjectWantedDto);
        return machineProjectDAO.saveProjectMachines(new AddProjectWantedCustom(addProjectWantedDto.getProjectId(),
                addProjectWantedDto.getProjectName(),addProjectWantedDto.getProjectDescription(),
                addProjectWantedDto.getCustomerId(),addProjectWantedDto.getProjectStartDate(),
                addProjectWantedDto.getProjectEndDate(),addProjectWantedDto.getUserId(),
                convertToProjectMaterialsEntities(addProjectWantedDto.getProjectMaterialsDtos()),
                convertToMachineProjectEntities(addProjectWantedDto.getMachineProjectDtos()),
                convertToPaymentEntities(addProjectWantedDto.getPaymentDtos())
        ));
    }

    private boolean updateMachineQuantities(AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        return machineDAO.updateMachineQuantities(addProjectWantedDto);
        return machineDAO.updateMachineQuantities(new AddProjectWantedCustom(addProjectWantedDto.getProjectId(),
                addProjectWantedDto.getProjectName(),addProjectWantedDto.getProjectDescription(),
                addProjectWantedDto.getCustomerId(),addProjectWantedDto.getProjectStartDate(),
                addProjectWantedDto.getProjectEndDate(),addProjectWantedDto.getUserId(),
                convertToProjectMaterialsEntities(addProjectWantedDto.getProjectMaterialsDtos()),
                convertToMachineProjectEntities(addProjectWantedDto.getMachineProjectDtos()),
                convertToPaymentEntities(addProjectWantedDto.getPaymentDtos())
        ));
    }

    private boolean savePayments(AddProjectWantedDto addProjectWantedDto) throws SQLException {
//        return paymentDAO.savePayments(addProjectWantedDto);
        return paymentDAO.savePayments(new AddProjectWantedCustom(addProjectWantedDto.getProjectId(),
                addProjectWantedDto.getProjectName(),addProjectWantedDto.getProjectDescription(),
                addProjectWantedDto.getCustomerId(),addProjectWantedDto.getProjectStartDate(),
                addProjectWantedDto.getProjectEndDate(),addProjectWantedDto.getUserId(),
                convertToProjectMaterialsEntities(addProjectWantedDto.getProjectMaterialsDtos()),
                convertToMachineProjectEntities(addProjectWantedDto.getMachineProjectDtos()),
                convertToPaymentEntities(addProjectWantedDto.getPaymentDtos())
        ));
    }
}
