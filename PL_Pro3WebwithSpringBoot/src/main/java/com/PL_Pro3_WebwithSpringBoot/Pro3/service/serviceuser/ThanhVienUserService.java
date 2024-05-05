/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser;

import java.util.List;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;

/**
 *
 * @author Lenovo
 */
public interface ThanhVienUserService {
    
    List<ThanhVienDTO> getAllThanhVien();
    
    ThanhVien AddThanhVien(ThanhVienDTO thanhVienDTO);

    ThanhVienDTO getThanhVienDTOById(int thanhVienID);
    
    ThanhVien getThanhVienById(int thanhVienID);

    void updateThanhVien(ThanhVienDTO thanhVienDTO);
    
    void deleteThanhVienByID(int thanhVienID);
}
