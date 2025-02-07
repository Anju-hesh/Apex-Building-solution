package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.RegistrationDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.UserAccountDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UseraccountFormBO extends SuperBO {

     String[] getDetails(String text) throws SQLException ;
     String getId(String text) throws SQLException ;
     boolean updateDetails(String password, String id) throws SQLException ;
     String validateLogin(String username, String password) throws SQLException ;
     String getUserId(String username , String password) throws SQLException ;
     String getNextuserId() throws SQLException, ClassNotFoundException;
     boolean registeredUser(RegistrationDto registrationDto) throws SQLException ;
     ArrayList<UserAccountDto> getAllUser() throws SQLException, ClassNotFoundException;
     boolean saveUser(UserAccountDto userAccountDto) throws SQLException, ClassNotFoundException;
     boolean deleteUser(String userId) throws SQLException, ClassNotFoundException;
     boolean updateUser(UserAccountDto userAccountDto) throws SQLException, ClassNotFoundException;
     UserAccountDto searchUser(String userId) throws SQLException, ClassNotFoundException;
     boolean confirmation(String userId, String password) throws SQLException ;
}
