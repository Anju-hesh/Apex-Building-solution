package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.CustomerFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.CustomerDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.CustomerDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormBOImpl implements CustomerFormBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    public String getNextCusomerId() throws SQLException, ClassNotFoundException {
       return customerDAO.getNextId();
    }
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDto> customerDTOs = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDto(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(),customer.getCustomerPhone()));
        }
        return customerDTOs;
    }
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDto.getCustomerId(),customerDto.getCustomerName(),customerDto.getCustomerAddress(),customerDto.getCustomerPhone()));
    }
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
       return customerDAO.update(new Customer(customerDto.getCustomerId(),customerDto.getCustomerName(),customerDto.getCustomerAddress(),customerDto.getCustomerPhone()));
    }
    public CustomerDto searchCustomer(String customerId) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(customerId);
        return new CustomerDto(customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerAddress(),customer.getCustomerPhone());
    }
}
