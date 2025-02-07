package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.UseraccountFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.UseraccountDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl.UseraccountDAOImpl;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.RegistrationDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.UserAccountDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Registration;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;

public class UseraccountFormBOImpl implements UseraccountFormBO {

    UseraccountDAO useraccountDAO = (UseraccountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USERACCOUNT);

    public String[] getDetails(String text) throws SQLException {
       return useraccountDAO.getDetails(text);
    }

    public String getId(String text) throws SQLException {
        return useraccountDAO.getId(text);
    }

    public boolean updateDetails(String password, String id) throws SQLException {
        return useraccountDAO.updateDetails(password, id);
    }
    public String validateLogin(String username, String password) throws SQLException {

        return useraccountDAO.validateLogin(username, password);
    }
    public String getUserId(String username , String password) throws SQLException {
        return useraccountDAO.getUserId(username, password);
    }
    public String getNextuserId() throws SQLException, ClassNotFoundException {

        return useraccountDAO.getNextId();
    }
    public boolean registeredUser(RegistrationDto registrationDto) throws SQLException {
       return useraccountDAO.registeredUser(new Registration(registrationDto.getUserId(),registrationDto.getFullName(),registrationDto.getUserName(),registrationDto.getPassword(),registrationDto.getConfirmPassword(),registrationDto.getEmployeeId(),registrationDto.getEmail()));
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
    public ArrayList<UserAccountDto> getAllUser() throws SQLException, ClassNotFoundException {
//        return useraccountDAO.getAll();
        ArrayList<UserAccountDto> userAccountDtos = new ArrayList<>();
        ArrayList<UserAccount> userAccounts = useraccountDAO.getAll();

        for (UserAccount userAccount : userAccounts) {
            userAccountDtos.add(new UserAccountDto(
                    userAccount.getUserId(),
                    userAccount.getFullName(),
                    userAccount.getUserName(),
                    userAccount.getPassword(),
                    userAccount.getEmployeeId(),
                    userAccount.getEmail()
            ));
        }

        return userAccountDtos;
    }
    public boolean saveUser(UserAccountDto userAccountDto) throws SQLException, ClassNotFoundException {
//        return useraccountDAO.save(userAccountDto);
        UserAccount userAccount = new UserAccount(
                userAccountDto.getUserId(),
                userAccountDto.getFullName(),
                userAccountDto.getUserName(),
                userAccountDto.getPassword(),
                userAccountDto.getEmployeeId(),
                userAccountDto.getEmail()
        );

        return useraccountDAO.save(userAccount);
    }

    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
       return useraccountDAO.delete(userId);
    }

    public boolean updateUser(UserAccountDto userAccountDto) throws SQLException, ClassNotFoundException {
//        return useraccountDAO.update(userAccountDto);
        UserAccount userAccount = new UserAccount(
                userAccountDto.getUserId(),
                userAccountDto.getFullName(),
                userAccountDto.getUserName(),
                userAccountDto.getPassword(),
                userAccountDto.getEmployeeId(),
                userAccountDto.getEmail()
        );

        return useraccountDAO.update(userAccount);
    }

    public UserAccountDto searchUser(String userId) throws SQLException, ClassNotFoundException {
//        return useraccountDAO.search(userId);
        UserAccount userAccount = useraccountDAO.search(userId);
        return new UserAccountDto(
                userAccount.getUserId(),
                userAccount.getFullName(),
                userAccount.getUserName(),
                userAccount.getPassword(),
                userAccount.getEmployeeId(),
                userAccount.getEmail()
        );
    }
    public boolean confirmation(String userId, String password) throws SQLException {
        return useraccountDAO.confirmation(userId, password);
    }
}
