/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;

/**
 *
 * @author Lenovo
 */
public interface ThanhVienUserService {
    boolean kiemTraDangNhap(int maTV, String password);
    ThanhVien getThanhVienById(int thanhVienID);
    ThanhVien addOrUpdateThanhVien(ThanhVien thanhVien);
}
