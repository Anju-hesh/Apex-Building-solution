package com.ijse.apexbuildingsolution.apex_building_solution.dao.custom;

import com.ijse.apexbuildingsolution.apex_building_solution.dao.CrudDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MaterialsDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Materials;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialDAO extends CrudDAO<Materials> {

//    String getNextMaterialID() throws SQLException;
//    ArrayList<MaterialsDto> getAllMaterials() throws SQLException;
//    boolean saveMaterials(MaterialsDto materialsDto) throws SQLException;
//    boolean deleteMaterials(String materialId) throws SQLException ;
//    boolean updateMaterials(MaterialsDto materialsDto) throws SQLException ;
//    MaterialsDto searchMaterials(String materialId) throws SQLException ;
    Materials findById(String selectedMaterialId) throws SQLException ;
    ArrayList<String> getAllMaterialIds() throws SQLException ;
}
