/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface ThanhVienAdminService {
    
    List<ThanhVienDTO> getAllThanhVien();
    
    ThanhVien AddThanhVien(ThanhVienDTO thanhVienDTO);

    ThanhVienDTO getThanhVienDTOById(int thanhVienID);
    
    ThanhVien getThanhVienById(int thanhVienID);

    void updateThanhVien(ThanhVienDTO thanhVienDTO);
    boolean updateThanhVien(int id, ThanhVienDTO thanhVienDTO);
    
    boolean deleteThanhVienByID(int thanhVienID);
    
    List<String> getAllNganh();   
    List<String> getAllKhoa();

    public boolean isMaTVExisting(int maTV);
    public boolean isExistingEmail(String email);
    public boolean isExistingSDT(String sdt);
}
