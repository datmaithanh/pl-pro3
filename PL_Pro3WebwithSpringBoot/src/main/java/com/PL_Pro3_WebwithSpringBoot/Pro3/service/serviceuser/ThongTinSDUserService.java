/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThongTinSDDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Lenovo
 */
public interface ThongTinSDUserService {
    public Optional<ThongTinSD> findByThanhVien(ThanhVien thanhVien);
    ThongTinSD addThongTinSD (ThongTinSDDTO thongTinSDDTO);
    
    ThongTinSD getThongTinSDByMaTB(int maTB);
    
    void updateThongTinSD (ThongTinSD thongTinSD);
    
    List<ThongTinSDDTO> getThongTinSDDaDatCho();

    List<ThongTinSDDTO> getThanhVienSDDaDatCho(int maThanhVien);

    List<ThongTinSDDTO> getThongTinSDDangMuonTB(int maThanhVien);

    void deleteThongTinSDId(int thongTinSDId);

}
