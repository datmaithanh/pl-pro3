/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser;

import java.util.List;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.XuLyDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;

/**
 *
 * @author Lenovo
 */
public interface XuLyUserService {
        public boolean checkMaSoSVTrongXuLy(int maSoSinhVien);
    List<XuLyDTO> getAllXuLy();
    
    XuLyDTO getXuLyByID(int xuLyID);
    
    List<XuLy> getXuLyByMaTV(int maTV);
}
