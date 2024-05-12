/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import java.util.Optional;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface ThanhVienUserService {

    public Optional<ThanhVien> findById(Integer id);
    public List<ThanhVien> findAll();
    
    boolean kiemTraDangNhap(int maTV, String password);
    ThanhVien addOrUpdateThanhVien(ThanhVien thanhVien);
    
    List<ThanhVienDTO> getAllThanhVien();
    
    ThanhVien AddThanhVien(ThanhVienDTO thanhVienDTO);

    ThanhVienDTO getThanhVienDTOById(int thanhVienID);
    
    ThanhVien getThanhVienById(int thanhVienID);

    void updateThanhVien(ThanhVienDTO thanhVienDTO);
    
    void deleteThanhVienByID(int thanhVienID);
}
