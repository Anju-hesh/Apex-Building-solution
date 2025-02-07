package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.CustomerDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {

//    String getNextCusomerId() throws SQLException;
//    ArrayList<CustomerDto> getAllCustomer() throws SQLException;
//    boolean saveCustomer(CustomerDto customerDto) throws SQLException;
//    boolean deleteCustomer(String customerId) throws SQLException;
//    boolean updateCustomer(CustomerDto customerDto) throws SQLException;
//    CustomerDto searchCustomer(String customerId) throws SQLException;
    ArrayList<String> getCustomerIds() throws SQLException;
}
