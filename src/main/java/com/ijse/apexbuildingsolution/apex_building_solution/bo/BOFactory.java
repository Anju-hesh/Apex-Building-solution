package com.ijse.apexbuildingsolution.apex_building_solution.bo;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        ADDPROJECTWANTED,CUSTOMER,EMPLOYEE,MACHINE,MACHINEPROJECT,MATERIAL,PAYMENT,PROJECT,PROJECTMATERIAL,REPAIR,SUPLIRE,USERACCOUNT
    }
    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerFormBOImpl();
            case EMPLOYEE:
                return new EmployeeFormBOImpl();
            case MACHINE:
                return new MachineFormBOImpl();
            case MACHINEPROJECT:
                return new MachineProjectBOImpl();
            case MATERIAL:
                return new MaterialBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PROJECT:
                return new ProjectFormBOImpl();
            case PROJECTMATERIAL:
                return new ProjectMaterialFormBOImpl();
            case REPAIR:
                return new RepairFormBOImpl();
            case SUPLIRE:
                return new SupplirFormBOImpl();
            case USERACCOUNT:
                return new UseraccountFormBOImpl();
            case ADDPROJECTWANTED:
                return new AddProjectFormBoImpl();
            default:
                return null;
        }
    }
}
