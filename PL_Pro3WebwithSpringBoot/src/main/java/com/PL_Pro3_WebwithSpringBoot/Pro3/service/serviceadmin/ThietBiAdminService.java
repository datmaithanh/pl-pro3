/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface ThietBiAdminService {
    List<ThietBiDTO> getAllThietBi();
    List<ThietBiDTO> getAllThietBiDTOs();
    
    ThietBi AddThietBi(ThietBiDTO thietBiDTO);

    ThietBiDTO getThietBiDTOByID(int thietBiID);
    ThietBi getThietBiByID(int thietBiID);

    void updateThietBi(ThietBiDTO thietBiDTO);
    boolean updateThietBi(int id, ThietBiDTO thietBiDTO);
    
    boolean deleteThietBiByID(int thietBiID);
    
    boolean isMaTBExisting(int maTB);
    List<ThietBiDTO> getThietBiNotInThongTinSDs();
    
    List<ThietBiDTO> getThietBiDaMuon();
}
