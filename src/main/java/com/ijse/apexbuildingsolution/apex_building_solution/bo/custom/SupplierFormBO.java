package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierFormBO extends SuperBO {

     String getNextSupplierId() throws SQLException, ClassNotFoundException;
     ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
     boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
     boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException;
     boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
     SupplierDto searchSupplier(String supplierId) throws SQLException, ClassNotFoundException;
}
