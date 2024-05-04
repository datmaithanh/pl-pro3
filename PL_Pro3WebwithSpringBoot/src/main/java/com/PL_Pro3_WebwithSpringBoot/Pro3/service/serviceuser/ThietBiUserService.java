/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser;

import java.util.List;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;

/**
 *
 * @author Lenovo
 */
public interface ThietBiUserService {
    List<ThietBiDTO> getAllThietBiDTOs();
    
    ThietBiDTO getThietBiDTOByID(int thietBiID);
    
    List<ThietBiDTO> getThietBiNotInThongTinSDs();
    
    ThietBi getThietBiByID(int thietBiID);
    
    List<ThietBiDTO> getThietBiDaMuon();

    List<ThietBiDTO> getSearchThietBiChuaMuon(String searchTerm);
}
