package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.SupplierFormBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.SuplireDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl.SuplireDAOImpl;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.PaymentDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.SupplierDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Payment;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplirFormBOImpl implements SupplierFormBO {

    SuplireDAO suplireDAO = (SuplireDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPLIRE);

    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
       return suplireDAO.getNextId();
    }
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
//        return suplireDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        ArrayList<Supplier> suppliers = suplireDAO.getAll();
        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getSupplierEmail(),supplier.getSupplierPhone()));
        }
        return supplierDtos;
    }

    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return suplireDAO.save(new Supplier(supplierDto.getSupplierId(),supplierDto.getSupplierName(),supplierDto.getSupplierAddress(),supplierDto.getSupplierEmail(),supplierDto.getSupplierPhone()));
    }

    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return suplireDAO.delete(supplierId);
    }

    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return suplireDAO.update(new Supplier(supplierDto.getSupplierId(),supplierDto.getSupplierName(),supplierDto.getSupplierAddress(),supplierDto.getSupplierEmail(),supplierDto.getSupplierPhone()));
    }

    public SupplierDto searchSupplier(String supplierId) throws SQLException, ClassNotFoundException {
//       return suplireDAO.search(supplierId);
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();

        Supplier supplier = suplireDAO.search(supplierId);
        SupplierDto supplierDto = new SupplierDto(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getSupplierEmail(),supplier.getSupplierPhone());
        supplierDtos.add(supplierDto);
        return supplierDtos.get(0);
    }
}
