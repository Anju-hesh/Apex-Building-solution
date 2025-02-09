package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.PaymentBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.PaymentDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.PaymentDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    public String getNextPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.getNextId();
    }
    public ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDto> payments = new ArrayList<>();
        ArrayList<Payment> pay = paymentDAO.getAll();
        for (Payment payment : pay) {
            payments.add(new PaymentDto(payment.getPaymentId(),payment.getPaymentMethod(),payment.getFullBalance(),payment.getPayedBalance(),payment.getProjectId(),payment.getStatus()));
        }
        return payments;
    }

    public boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(paymentDto.getPaymentId(),paymentDto.getPaymentMethod(),paymentDto.getFullBalance(),paymentDto.getPayedBalance(),paymentDto.getProjectId(),paymentDto.getStatus()));
    }

    public boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(paymentId);
    }

    public boolean updatePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment( paymentDto.getPaymentId(),paymentDto.getPaymentMethod(),paymentDto.getFullBalance(),paymentDto.getPayedBalance(),paymentDto.getProjectId() ,paymentDto.getStatus()));
    }

    public PaymentDto searchPayment(String projectId) throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        Payment payment = paymentDAO.search(projectId);
        PaymentDto paymentDto = new PaymentDto(payment.getPaymentId(),payment.getPaymentMethod(),payment.getFullBalance(),payment.getPayedBalance() , payment.getProjectId() ,payment.getStatus());
        paymentDtos.add(paymentDto);
        return paymentDtos.get(0);
    }
}
