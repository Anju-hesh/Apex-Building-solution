package com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.impl;

import com.ijse.apexbuildingsolution.apex_building_solution.bo.custom.MaterialBO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.DAOFactory;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.MaterialDAO;
import com.ijse.apexbuildingsolution.apex_building_solution.dao.custom.impl.MaterialDAOImpl;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MachineProjectDto;
import com.ijse.apexbuildingsolution.apex_building_solution.dto.MaterialsDto;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.MachineProject;
import com.ijse.apexbuildingsolution.apex_building_solution.entity.Materials;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialBOImpl implements MaterialBO {

    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL);

    public String getNextMaterialID() throws SQLException, ClassNotFoundException {
        return materialDAO.getNextId();
    }
    public ArrayList<MaterialsDto> getAllMaterials() throws SQLException, ClassNotFoundException {
//       return materialDAO.getAll();
        ArrayList<MaterialsDto> materialsDtos = new ArrayList<>();
        ArrayList<Materials> materials =materialDAO.getAll();
        for (Materials material : materials) {
            materialsDtos.add(new MaterialsDto(material.getMaterialId(),material.getMaterialName(),material.getQtyOnHand(),material.getModelNumber(),material.getAmount(),material.getSuplierId()));
        }
        return materialsDtos;
    }

    public boolean saveMaterials(MaterialsDto material) throws SQLException, ClassNotFoundException {
       return materialDAO.save(new Materials(material.getMaterialId(),material.getMaterialName(),material.getQtyOnHand(),material.getModelNumber(),material.getAmount(),material.getSuplierId()));
    }

    public boolean deleteMaterials(String materialId) throws SQLException, ClassNotFoundException {
        return materialDAO.delete(materialId);
    }

    public boolean updateMaterials(MaterialsDto material) throws SQLException, ClassNotFoundException {
        return materialDAO.update(new Materials(material.getMaterialId(),material.getMaterialName(),material.getQtyOnHand(),material.getModelNumber(),material.getAmount(),material.getSuplierId()));
    }

    public MaterialsDto searchMaterials(String materialId) throws SQLException, ClassNotFoundException {
//        return materialDAO.search(materialId)
        ArrayList<MaterialsDto> matdtosmatdtos = new ArrayList<>();

        Materials mat = materialDAO.search(materialId);
        MaterialsDto materialsDto = new MaterialsDto(mat.getMaterialId() , mat.getMaterialName(),mat.getQtyOnHand(),mat.getModelNumber(),mat.getAmount(),mat.getSuplierId());
        matdtosmatdtos.add(materialsDto);
        return materialsDto;

    }
    public MaterialsDto findById(String selectedMaterialId) throws SQLException {
//        return materialDAO.findById(selectedMaterialId);
        ArrayList<MaterialsDto> materialsDtos = new ArrayList<>();
        Materials mat = materialDAO.findById(selectedMaterialId);
        MaterialsDto materialsDto = new MaterialsDto(mat.getMaterialId() , mat.getMaterialName(),mat.getQtyOnHand(),mat.getModelNumber(),mat.getAmount(),mat.getSuplierId());
        materialsDtos.add(materialsDto);
        return materialsDto;
    }

    public ArrayList<String> getAllMaterialIds() throws SQLException {
        return materialDAO.getAllMaterialIds();
    }
}
