package com.ijse.apexbuildingsolution.apex_building_solution.dao;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        ADDPROJECTWANTED,CUSTOMER,EMPLOYEE,MACHINE,MACHINEPROJECT,MATERIAL,PAYMENT,PROJECT,PROJECTMATERIAL,REPAIR,SUPLIRE,USERACCOUNT,QUERY
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MACHINE:
                return new MachineDAOImpl();
            case MACHINEPROJECT:
                return new MachineProjectDAOImpl();
            case MATERIAL:
                return new MaterialDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PROJECT:
                return new ProjectDAOImpl();
            case PROJECTMATERIAL:
                return new ProjectMaterialDAOImpl();
            case REPAIR:
                return new RepairDAOImpl();
            case SUPLIRE:
                return new SuplireDAOImpl();
            case USERACCOUNT:
                return new UseraccountDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
//            case ADDPROJECTWANTED:
//                return new AddProjectWantedDAOImpl();
            default:
                return null;
        }
    }
}
