/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.XuLyDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface XuLyAdminService {
    
    
    public boolean checkMaSoSVTrongXuLy(int maSoSinhVien);
    List<XuLyDTO> getAllXuLy();
    
    XuLyDTO getXuLyByID(int xuLyID);
    
    List<XuLy> getXuLyByMaTV(int maTV);
}
