/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThongTinSDDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface ThongTinSDAdminService {
    ThongTinSD addThongTinSD (ThongTinSDDTO thongTinSDDTO);
    
    ThongTinSD getThongTinSDByMaTB(int maTB);
    
    void updateThongTinSD (ThongTinSD thongTinSD);
    
    List<ThongTinSDDTO> getThongTinSDDaDatCho();
}
