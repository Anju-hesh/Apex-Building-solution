package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {

     String getNextPaymentId() throws SQLException, ClassNotFoundException;
     ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException;
     boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException;
     boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException;
     boolean updatePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException;
     PaymentDto searchPayment(String projectId) throws SQLException, ClassNotFoundException;
}
