package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudUtil;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.MaterialDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MaterialsDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Materials;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDAOImpl implements MaterialDAO {

    public String getNextId() throws SQLException {
        String sql = "SELECT MaterialID FROM material ORDER BY MaterialID DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String materialID = resultSet.getString(1);
            String submaterialID = materialID.substring(4);
            int lastIdIndex = Integer.parseInt(submaterialID);
            int nextIndex = lastIdIndex + 1;
            String newId = String.format("MAT%03d", nextIndex);
            return newId;
        }
        return "MAT001";
    }
    public ArrayList<Materials> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM material");
        ArrayList<Materials> materialsDtos = new ArrayList<>();

        while (rst.next()) {
            Materials materials = new Materials(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6)
            );
            materialsDtos.add(materials);
        }
        return materialsDtos;
    }

    public boolean save(Materials materials) throws SQLException {
        String sql = "INSERT INTO material (MaterialID, Name, Qty_On_Hand, ModelNo , Amount , SupplierID) VALUES (?, ?, ?, ? , ? , ?)";
        return CrudUtil.execute(sql, materials.getMaterialId(), materials.getMaterialName(),materials.getQtyOnHand(), materials.getModelNumber(),materials.getAmount(),materials.getSuplierId());
    }

    public boolean delete(String materialId) throws SQLException {
        String sql = "DELETE FROM material WHERE MaterialID = ?";
        return CrudUtil.execute(sql, materialId);
    }

    public boolean update(Materials materials) throws SQLException {
        String sql = "UPDATE material SET Name = ?, Qty_On_Hand = ?, ModelNo = ?, Amount = ? , SupplierID = ?  WHERE MaterialID = ?";
        return CrudUtil.execute(sql, materials.getMaterialName(), materials.getQtyOnHand(), materials.getModelNumber(), materials.getAmount(),materials.getSuplierId(), materials.getMaterialId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Materials search(String materialId) throws SQLException {
        String sql = "SELECT * FROM material WHERE MaterialID = ?";
        ResultSet rst = CrudUtil.execute(sql, materialId);
        if (rst.next()) {
            return new Materials(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6)
            );
        }
        return null;
    }
    public Materials findById(String selectedMaterialId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM material WHERE Name = ?", selectedMaterialId);

        if (rst.next()) {
            return new Materials(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    public ArrayList<String> getAllMaterialIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT Name FROM material");

        ArrayList<String> materialIds = new ArrayList<>();

        while (rst.next()) {
            materialIds.add(rst.getString(1));
        }
        return materialIds;
    }
}
