package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeFormBO extends SuperBO {

     String getNextEmployeeId() throws SQLException, ClassNotFoundException;
     ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;
     boolean saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
     boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;
     boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
     EmployeeDto searchEmployee(String employeeId) throws SQLException, ClassNotFoundException;
}
