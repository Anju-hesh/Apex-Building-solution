package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.UseraccountDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.db.DBConnection;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.RegistrationDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.UserAccountDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Registration;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.UserAccount;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UseraccountDAOImpl implements UseraccountDAO {

    public String[] getDetails(String text) throws SQLException {
        ResultSet rs = CrudUtil.execute("select User_name,Email from useraccount where Email = ?",text);
        String[] details = {"","0"};
        while (rs.next()) {
            details[0] = rs.getString("User_name");
            details[1] = rs.getString("Email");
        }
        return details;
    }

    public String getId(String text) throws SQLException {
        ResultSet rs = CrudUtil.execute("select UserID from useraccount where Email = ?",text);
        String id = "";
        while (rs.next()) {
            id = rs.getString("UserID");
        }
        return id;
    }

    public boolean updateDetails(String password, String id) throws SQLException {
        return CrudUtil.execute("update useraccount set Password = ? where UserID = ?",password,id);
    }
    public String validateLogin(String username, String password) throws SQLException {

        String sql = "SELECT COUNT(1) FROM useraccount WHERE User_name = ? AND Password = ?";

        // String sql = "SELECT password FROM useraccount WHERE User_name = ?";

        Connection connection = DBConnection.getInstance().getCONNECTION();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) == 1) {
            return "Congrats...!";
        } else {
            return "Invalid username or password!";
        }
    }
    public String getUserId(String username , String password) throws SQLException {
        String sql = "SELECT UserId FROM useraccount WHERE User_name = ? and Password = ?";
        Connection connection = DBConnection.getInstance().getCONNECTION();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("UserId");
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Invalid username or password!" + e.getMessage()).showAndWait();
        }
        return null;
    }
    public String getNextId() throws SQLException {

        String sql = "SELECT UserID FROM useraccount ORDER BY UserID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String userId = resultSet.getString(1); // USER001
            String subuserId = userId.substring(4); // 001
            int lastIdIndex = Integer.parseInt(subuserId); // 1
            int nextIndex = lastIdIndex + 1; // 2
            String newId = String.format("USER%03d", nextIndex); // USER002
            return newId;
        }
        return "USER001";
    }
    public boolean registeredUser(Registration registration) throws SQLException {
        String sql = "INSERT INTO useraccount (UserID, Full_Name, User_name, Password , EmployeeID , Email) VALUES (?, ?, ?, ? ,? ,?)";
        return CrudUtil.execute(sql, registration.getUserId(), registration.getFullName(), registration.getUserName(), registration.getPassword(), registration.getEmployeeId(), registration.getEmail());
    }
//    public String getNextUserId() throws SQLException {
//
//        String sql = "SELECT UserID FROM useraccount ORDER BY UserID DESC LIMIT 1";
//
//        ResultSet resultSet = CrudUtil.execute(sql);
//        if (resultSet.next()){
//            String userId = resultSet.getString(1); // USER001
//            String subuserId = userId.substring(4); // 001
//            int lastIdIndex = Integer.parseInt(subuserId); // 1
//            int nextIndex = lastIdIndex + 1; // 2
//            String newId = String.format("USER%03d", nextIndex); // USER002
//            return newId;
//        }
//        return "USER001";
//    }
    public ArrayList<UserAccount> getAllUser() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM useraccount");
        ArrayList<UserAccount> userDTOS = new ArrayList<>();

        while (rst.next()) {
            UserAccount user = new UserAccount(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
            userDTOS.add(user);
        }
        return userDTOS;
    }

    @Override
    public ArrayList<UserAccount> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(UserAccount userAccount) throws SQLException {
        String sql = "INSERT INTO useraccount (UserID, Full_Name, User_name, Password , EmployeeID , Email) VALUES (?, ?, ?, ? ,? ,?)";
        return CrudUtil.execute(sql, userAccount.getUserId(), userAccount.getFullName(), userAccount.getUserName(), userAccount.getPassword(),userAccount.getEmployeeId(),userAccount.getEmail());
    }

    public boolean delete(String userId) throws SQLException {
        String sql = "DELETE FROM useraccount WHERE UserID = ? ";
        return CrudUtil.execute(sql, userId);
    }

    public boolean update(UserAccount userAccount) throws SQLException {
        String sql = "UPDATE useraccount SET Full_Name = ?, User_name = ?, Password = ?, EmployeeID = ? , Email = ? WHERE UserID = ? ";
        return CrudUtil.execute(sql, userAccount.getFullName(), userAccount.getUserName(), userAccount.getPassword(),userAccount.getEmployeeId(),userAccount.getEmail(),userAccount.getUserId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public UserAccount search(String userId) throws SQLException {
        String sql = "SELECT * FROM useraccount WHERE UserID = ? ";
        ResultSet rst = CrudUtil.execute(sql, userId);
        if (rst.next()) {
            return new UserAccount(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }
    public boolean confirmation(String userId, String password) throws SQLException {
        Connection connection = DBConnection.getInstance().getCONNECTION();
        String sql = "SELECT Password FROM useraccount WHERE UserID = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, userId); // Set the user ID in the SQL query

        ResultSet resultSet = pst.executeQuery();

        if (resultSet.next()) {
            String storedPassword = resultSet.getString("Password"); // Get the stored password
            return storedPassword.equals(password); // Compare with input password
        }
        return false; // Return false if no user found or password doesn't match
    }
}
