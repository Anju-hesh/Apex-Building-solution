package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.PaymentDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Payment;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.custom.AddProjectWantedCustom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
//    String getNextPaymentId() throws SQLException;
//    ArrayList<PaymentDto> getAllPayments() throws SQLException;
//    boolean savePayment(PaymentDto paymentDto) throws SQLException;
//    boolean deletePayment(String paymentId) throws SQLException;
//    boolean updatePayment(PaymentDto paymentDto) throws SQLException;
//    PaymentDto searchPayment(String projectId) throws SQLException;
     boolean savePayments(AddProjectWantedCustom addProjectWantedDto) throws SQLException ;
}
