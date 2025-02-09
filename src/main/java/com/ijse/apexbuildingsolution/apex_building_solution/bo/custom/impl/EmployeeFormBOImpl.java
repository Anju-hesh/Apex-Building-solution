package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.EmployeeFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.EmployeeDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.EmployeeDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeFormBOImpl implements EmployeeFormBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNextId();
    }
    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employeeDTOs = new ArrayList<>();
        ArrayList<Employee> employees = employeeDAO.getAll();
        for (Employee employee : employees) {
            employeeDTOs.add(new EmployeeDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getRole(),employee.getAddress(),employee.getSalary(),employee.getPhone(),employee.getAttendents()));
        }
        return employeeDTOs;    }
    public boolean saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDto.getEmployeeId(),employeeDto.getEmployeeName(),employeeDto.getRole(),employeeDto.getAddress(),employeeDto.getSalary(),employeeDto.getPhone(),employeeDto.getAttendents()));
    }
    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
       return employeeDAO.delete(employeeId);
    }

    public boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDto.getEmployeeId(),employeeDto.getEmployeeName(),employeeDto.getRole(),employeeDto.getAddress(),employeeDto.getSalary(),employeeDto.getPhone(),employeeDto.getAttendents()));
    }
    public EmployeeDto searchEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(employeeId);
        return new EmployeeDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getRole(),employee.getAddress(),employee.getSalary(),employee.getPhone(),employee.getAttendents());
    }
}
