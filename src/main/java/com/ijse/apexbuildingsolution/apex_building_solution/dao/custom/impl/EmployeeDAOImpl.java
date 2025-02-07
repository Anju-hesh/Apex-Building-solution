package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.EmployeeDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.EmployeeDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public String getNextId() throws SQLException {
        String sql = "SELECT EmployeeID FROM employee ORDER BY EmployeeID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String employeeId = resultSet.getString(1);
            String subemployeeId = employeeId.substring(4);
            int lastIdIndex = Integer.parseInt(subemployeeId);
            int nextIndex = lastIdIndex + 1;
            String newId = String.format("EMP%03d", nextIndex);
            return newId;
        }
        return "EMP001";
    }
    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> employeeDtos = new ArrayList<>();

        while (rst.next()) {
            Employee employee = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7)
            );
            employeeDtos.add(employee);
        }
        return employeeDtos;
    }
    public boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (EmployeeID, Name, Role, Address ,Salary , ContactNo , Attendance) VALUES (?, ?, ?, ? ,? ,? ,?)";
        return CrudUtil.execute(sql, employee.getEmployeeId(), employee.getEmployeeName(), employee.getRole(), employee.getAddress(),employee.getSalary() ,employee.getPhone() , employee.getAttendents());
    }
    public boolean delete(String employeeId) throws SQLException {
        String sql = "DELETE FROM employee WHERE EmployeeID = ? ";
        return CrudUtil.execute(sql, employeeId);
    }

    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET Name = ?, Role = ?, Address = ? , Salary = ? , ContactNo = ? , Attendance = ?  WHERE EmployeeID = ?";
        return CrudUtil.execute(sql, employee.getEmployeeName(), employee.getRole(), employee.getAddress(),employee.getSalary() ,employee.getPhone() , employee.getAttendents() , employee.getEmployeeId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Employee search(String employeeId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmployeeID = ? ";
        ResultSet rst = CrudUtil.execute(sql, employeeId);
        if (rst.next()) {
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }
        return null;
    }
}
