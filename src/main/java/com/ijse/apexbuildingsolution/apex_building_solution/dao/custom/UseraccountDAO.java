package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.db.DBConnection;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.RegistrationDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.UserAccountDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Registration;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UseraccountDAO extends CrudDAO<UserAccount> {

     String[] getDetails(String text) throws SQLException ;
     String getId(String text) throws SQLException ;
     boolean updateDetails(String password, String id) throws SQLException ;
     String validateLogin(String username, String password) throws SQLException;
     String getUserId(String username , String password) throws SQLException;
//     String getNextuserId() throws SQLException;
     boolean registeredUser(Registration registration) throws SQLException ;
//     ArrayList<UserAccountDto> getAllUser() throws SQLException ;
//     boolean saveUser(UserAccountDto userAccountDto) throws SQLException ;
//     boolean deleteUser(String userId) throws SQLException ;
//     boolean updateUser(UserAccountDto userAccountDto) throws SQLException ;
//     UserAccountDto searchUser(String userId) throws SQLException ;
     boolean confirmation(String userId, String password) throws SQLException ;
}
