package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.CustomerDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.CustomerDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public String getNextId() throws SQLException {
        String sql = "SELECT CustomerId FROM customer ORDER BY CustomerId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String customerId = resultSet.getString(1);
            String subcustomerId = customerId.substring(4);
            int lastIdIndex = Integer.parseInt(subcustomerId);
            int nextIndex = lastIdIndex + 1;
            String newId = String.format("CUST%03d", nextIndex);
            return newId;
        }
        return "CUST001";
    }
    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString(1), // CustomerId
                    rst.getString(2), // CustomerName
                    rst.getString(3), // CustomerAddress
                    rst.getString(4)  // CustomerPhone
            );
            customerDTOS.add(customer);
        }
        return customerDTOS;
    }
    public boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (CustomerId, Name, Address, Contact_Tel) VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerPhone());
    }
    public boolean delete(String customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE CustomerId = ?";
        return CrudUtil.execute(sql, customerId);
    }
    public boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET Name = ?, Address = ?, Contact_Tel = ? WHERE CustomerId = ?";
        return CrudUtil.execute(sql, customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerPhone(), customer.getCustomerId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Customer search(String customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE CustomerId = ?";
        ResultSet rst = CrudUtil.execute(sql, customerId);
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }
    public ArrayList<String> getCustomerIds() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT CustomerId FROM Customer");
        ArrayList<String> customerIds = new ArrayList<>();

        while (resultSet.next()){
            customerIds.add(resultSet.getString(1));
        }
        return customerIds;
    }
}
