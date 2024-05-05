/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impluser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThanhVienUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class ThanhVienUserServiceImpl implements ThanhVienUserService{
    private ThanhVienRepository thanhVienRepository;
    
    @Autowired
    public ThanhVienUserServiceImpl(ThanhVienRepository thanhVienRepository) {
        this.thanhVienRepository = thanhVienRepository;
    }

    @Override
    public boolean kiemTraDangNhap(int maTV, String password) {
        ThanhVien thanhVien = thanhVienRepository.findByMaTVAndPassword(maTV, password);
        if (thanhVien != null)
            return true;
        return false;
    }
    
    @Override
    public ThanhVien getThanhVienById(int maTV) {
        ThanhVien thanhVien = thanhVienRepository.findById(maTV).orElse(null);
        if (thanhVien != null) {
            return thanhVien;
        } else {
            return null;
        }   
    }

    @Override
    public ThanhVien addOrUpdateThanhVien(ThanhVien thanhVien) {
        return thanhVienRepository.save(thanhVien);
    }
}
