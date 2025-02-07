package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.SuplireDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.SupplierDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuplireDAOImpl implements SuplireDAO {

    public String getNextId() throws SQLException {
        String sql = "SELECT SupplierID FROM supplier ORDER BY SupplierID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String supplierId = resultSet.getString(1);
            String subsupplierId = supplierId.substring(4);
            int lastIdIndex = Integer.parseInt(subsupplierId);
            int nextIndex = lastIdIndex + 1;
            String newId = String.format("SUP%03d", nextIndex);
            return newId;
        }
        return "SUP001";
    }
    public ArrayList<Supplier> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> supplierDtos = new ArrayList<>();

        while (rst.next()) {
            Supplier supplier = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            supplierDtos.add(supplier);
        }
        return supplierDtos;
    }

    public boolean save(Supplier supplier) throws SQLException {
        String sql = " INSERT INTO supplier (SupplierID, Name, Address, Email , ContactNo) VALUES ( ? , ?, ?, ? , ? ) ";
        return CrudUtil.execute(sql, supplier.getSupplierId(), supplier.getSupplierName(),supplier.getSupplierAddress(), supplier.getSupplierEmail() ,supplier.getSupplierPhone());
    }

    public boolean delete(String supplierId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE SupplierID = ? ";
        return CrudUtil.execute(sql, supplierId);
    }

    public boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET Name = ?, Address = ?, Email = ? , ContactNo = ?  WHERE SupplierID = ? ";
        return CrudUtil.execute(sql, supplier.getSupplierName(), supplier.getSupplierAddress(), supplier.getSupplierEmail(), supplier.getSupplierPhone(), supplier.getSupplierId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Supplier search(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE SupplierID = ?";
        ResultSet rst = CrudUtil.execute(sql, supplierId);
        if (rst.next()) {
            return new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }
}
