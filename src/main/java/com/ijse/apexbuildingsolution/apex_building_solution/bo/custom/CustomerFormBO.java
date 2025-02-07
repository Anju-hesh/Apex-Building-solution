package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerFormBO extends SuperBO {

    String getNextCusomerId() throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    CustomerDto searchCustomer(String customerId) throws SQLException, ClassNotFoundException;
}
