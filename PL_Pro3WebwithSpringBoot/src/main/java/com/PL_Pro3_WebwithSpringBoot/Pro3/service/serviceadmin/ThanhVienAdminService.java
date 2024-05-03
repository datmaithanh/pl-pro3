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

    ThanhVienDTO getThanhVienById(int thanhVienID);

    void updateThanhVien(int id, ThanhVienDTO thanhVienDTO);
    
    void deleteThanhVienByID(int thanhVienID);
    
//    void deleteByYear(int year);
}



