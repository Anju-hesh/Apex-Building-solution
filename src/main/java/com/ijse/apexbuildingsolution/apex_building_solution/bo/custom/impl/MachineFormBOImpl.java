package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.MachineFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.MachineDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl.MachineDAOImpl;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.CustomerDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Customer;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Machine;

import java.sql.SQLException;
import java.util.ArrayList;

public class MachineFormBOImpl implements MachineFormBO {

    MachineDAO machineDAO = (MachineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MACHINE);

    public String getNextMachineId() throws SQLException, ClassNotFoundException {
        return machineDAO.getNextId();
    }
    public ArrayList<MachineDto> getAllMachinne() throws SQLException, ClassNotFoundException {
        ArrayList<MachineDto> machineDtos = new ArrayList<>();
        ArrayList<Machine> machines = machineDAO.getAll();
        for (Machine machine : machines) {
            machineDtos.add(new MachineDto(machine.getMachineId(),machine.getMachineName(),machine.isAvailability(),machine.getStatus(),machine.getQtyOnHand()));
        }
        return machineDtos;
    }
    public boolean saveMachine(MachineDto machineDto) throws SQLException, ClassNotFoundException {
        return machineDAO.save(new Machine(machineDto.getMachineId(),machineDto.getMachineName(),machineDto.isAvailability(),machineDto.getStatus(),machineDto.getQtyOnHand()));
    }

    public boolean deleteMachine(String machineId) throws SQLException, ClassNotFoundException {
        return machineDAO.delete(machineId);
    }

    public boolean updateMachine(MachineDto machineDto) throws SQLException, ClassNotFoundException {
        return machineDAO.update(new Machine(machineDto.getMachineId(),machineDto.getMachineName(),machineDto.isAvailability(),machineDto.getStatus(),machineDto.getQtyOnHand()));
    }
    public MachineDto searchMachine(String machineId) throws SQLException, ClassNotFoundException {
        Machine machine = machineDAO.search(machineId);
        return new MachineDto(machine.getMachineId(), machine.getMachineName(), machine.isAvailability(), machine.getStatus(), machine.getQtyOnHand());
    }
    public MachineDto findById(String selectedMachineId) throws SQLException {

        Machine machine = machineDAO.findById(selectedMachineId);
        return new MachineDto(machine.getMachineId(), machine.getMachineName(), machine.isAvailability(), machine.getStatus(), machine.getQtyOnHand());
    }
    public ArrayList<String> getAllMachineIds() throws SQLException {
        return machineDAO.getAllMachineIds();
    }
}
