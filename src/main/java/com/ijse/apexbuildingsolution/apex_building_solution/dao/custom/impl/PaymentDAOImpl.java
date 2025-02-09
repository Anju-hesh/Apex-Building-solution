package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.PaymentDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.AddProjectWantedDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.PaymentDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Payment;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.custom.AddProjectWantedCustom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    public String getNextId() throws SQLException {
        String sql = "SELECT PaymentID FROM payment ORDER BY PaymentID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String paymentId = resultSet.getString(1);
            String subpaymentId = paymentId.substring(4);
            int lastIdIndex = Integer.parseInt(subpaymentId);
            int nextIndex = lastIdIndex + 1;
            String newId = String.format("PAY%03d", nextIndex);
            return newId;
        }
        return "PAY001";
    }
    public ArrayList<Payment> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM payment");
        ArrayList<Payment> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            Payment payment = new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getString(5),
                    rst.getString(6)
            );
            paymentDtos.add(payment);
        }
        return paymentDtos;
    }

    public boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (PaymentID, Method , FullBalance , PayedBalance ,ProjectId, Status ) VALUES (?,?,?,?,?,?) ";
        return CrudUtil.execute(sql, payment.getPaymentId(), payment.getPaymentMethod(), payment.getFullBalance(), payment.getPayedBalance(), payment.getProjectId(),payment.getStatus());
    }

    public boolean delete(String paymentId) throws SQLException {
        String sql = "DELETE FROM payment WHERE PaymentID = ?";
        return CrudUtil.execute(sql, paymentId);
    }

    public boolean update(Payment payment) throws SQLException {
        String sql = "UPDATE payment SET Method = ?,FullBalance = ?,PayedBalance = ?,ProjectId =? ,Status = ? WHERE PaymentID = ?";
        return CrudUtil.execute(sql, payment.getPaymentMethod(), payment.getFullBalance(), payment.getPayedBalance(), payment.getProjectId(),payment.getStatus() , payment.getPaymentId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Payment search(String projectId) throws SQLException {
        String sql = "SELECT * FROM payment WHERE ProjectId = ?";
        ResultSet rst = CrudUtil.execute(sql, projectId);
        if (rst.next()) {
            return new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }
    public boolean savePayments(AddProjectWantedCustom addProjectWantedDto) throws SQLException {
        for (Payment dto : addProjectWantedDto.getPaymentDtos()) {
            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO payment VALUES (?,?,?,?,?,?)",
                    dto.getPaymentId(),
                    dto.getPaymentMethod(),
                    dto.getFullBalance(),
                    dto.getPayedBalance(),
                    dto.getProjectId(),
                    dto.getStatus()
            );

            if (!isSaved) return false;
        }
        return true;
    }
}
