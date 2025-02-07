package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.MachineProjectBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.MachineProjectDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl.MachineProjectDAOImpl;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Machine;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.MachineProject;

import java.sql.SQLException;
import java.util.ArrayList;

public class MachineProjectBOImpl implements MachineProjectBO {

    MachineProjectDAO machineProjectDAO = (MachineProjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MACHINEPROJECT);

    public String getNextProjectID() throws SQLException, ClassNotFoundException {
        return machineProjectDAO.getNextId();
    }
    public boolean saveMachineProject(MachineProjectDto machineProjectDto) throws SQLException, ClassNotFoundException {
        return machineProjectDAO.save(new MachineProject(machineProjectDto.getProjectId(),machineProjectDto.getMachineId(),machineProjectDto.getQty()));
    }

    public boolean deleteMachineProject(String projectId) throws SQLException, ClassNotFoundException {
        return machineProjectDAO.delete(projectId);
    }

    public boolean updateMachineProject(MachineProjectDto machineProjectDto) throws SQLException, ClassNotFoundException {
        return machineProjectDAO.update(new MachineProject(machineProjectDto.getProjectId(),machineProjectDto.getMachineId(),machineProjectDto.getQty()));
    }

    public ArrayList<MachineProjectDto> searchMachineProject(String projectId) throws SQLException, ClassNotFoundException {
//        return machineProjectDAO.searchMachineProject(projectId);
        ArrayList<MachineProjectDto> machineProjectDtos = new ArrayList<>();

        MachineProject machineProject = machineProjectDAO.search(projectId);
        MachineProjectDto machineProjectDto = new MachineProjectDto(machineProject.getProjectId(), machineProject.getMachineId(), machineProject.getQty());
        machineProjectDtos.add(machineProjectDto);
        return machineProjectDtos;
    }
    public ArrayList<MachineProjectDto> getAllMachineProjectDetails() throws SQLException, ClassNotFoundException {
        ArrayList<MachineProjectDto> machineProjectDtos = new ArrayList<>();
        ArrayList<MachineProject> machineprojects = machineProjectDAO.getAll();
        for (MachineProject machineProject : machineprojects) {
            machineProjectDtos.add(new MachineProjectDto(machineProject.getProjectId(),machineProject.getMachineId(),machineProject.getQty()));
        }
        return machineProjectDtos;
    }
}
