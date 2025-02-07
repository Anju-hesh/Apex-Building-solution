package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.SuperBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MaterialsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialBO extends SuperBO {

     String getNextMaterialID() throws SQLException, ClassNotFoundException;
     ArrayList<MaterialsDto> getAllMaterials() throws SQLException, ClassNotFoundException;
     boolean saveMaterials(MaterialsDto materialsDto) throws SQLException, ClassNotFoundException;
     boolean deleteMaterials(String materialId) throws SQLException, ClassNotFoundException;
     boolean updateMaterials(MaterialsDto materialsDto) throws SQLException, ClassNotFoundException;
     MaterialsDto searchMaterials(String materialId) throws SQLException, ClassNotFoundException;
     MaterialsDto findById(String selectedMaterialId) throws SQLException ;
     ArrayList<String> getAllMaterialIds() throws SQLException ;
}
