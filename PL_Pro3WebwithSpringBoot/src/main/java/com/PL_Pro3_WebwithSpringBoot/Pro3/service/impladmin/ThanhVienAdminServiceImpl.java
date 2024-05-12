/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impladmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThanhVienAdminService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class ThanhVienAdminServiceImpl implements ThanhVienAdminService{
    
    private ThanhVienRepository thanhVienRepository;
    @Autowired
    public ThanhVienAdminServiceImpl(ThanhVienRepository thanhVienRepository) {
        this.thanhVienRepository = thanhVienRepository;
    }
    
   
    
    
    @Override
    public List<ThanhVienDTO> getAllThanhVien() {
        List<ThanhVien> thanhViens = thanhVienRepository.findAll();
        return thanhViens.stream().map((thanhVien ->mapToThanhVienDTO(thanhVien))).collect(Collectors.toList());
    }
    private  ThanhVienDTO mapToThanhVienDTO(ThanhVien thanhVien){
        ThanhVienDTO thanhVienDTO = ThanhVienDTO.builder()
                .maTV(thanhVien.getMaTV())
                .hoTen(thanhVien.getHoTen())
                .khoa(thanhVien.getKhoa())
                .nganh(thanhVien.getNganh())
                .sdt(thanhVien.getSdt())
                .email(thanhVien.getEmail())
                .password(thanhVien.getPassword())
                .build();

        return thanhVienDTO;
    }

    
    
    @Override
    public ThanhVien AddThanhVien(ThanhVienDTO thanhVienDTO) {
        ThanhVien thanhVien = mapToThanhVien(thanhVienDTO);
        return thanhVienRepository.save(thanhVien);
    }
    
    private ThanhVien mapToThanhVien (ThanhVienDTO thanhVienDTO) {
        ThanhVien thanhVien = ThanhVien.builder()
                .maTV(thanhVienDTO.getMaTV())
                .hoTen(thanhVienDTO.getHoTen())
                .khoa(thanhVienDTO.getKhoa())
                .nganh(thanhVienDTO.getNganh())
                .sdt(thanhVienDTO.getSdt())
                .email(thanhVienDTO.getEmail())
                .password(thanhVienDTO.getPassword())
                .build();
        return thanhVien;
    }
    @Override
    public ThanhVienDTO getThanhVienById(int thanhVienID) {
         ThanhVien thanhVien = thanhVienRepository.findById(thanhVienID).get();
        return mapToThanhVienDTO(thanhVien);
    }
   
    @Override
    public ThanhVienDTO getThanhVienDTOById(int thanhVienID) {
        ThanhVien thanhVien = thanhVienRepository.findById(thanhVienID).orElse(null);
        if (thanhVien != null) {
            return mapToThanhVienDTO(thanhVien);
        } else {
            return null;
        }
    }

    @Override
    public void updateThanhVien(ThanhVienDTO thanhVienDTO) {
        ThanhVien thanhVien = mapToThanhVien(thanhVienDTO);
        thanhVienRepository.save(thanhVien);
    }

    @Override
    public void deleteThanhVienByID(int thanhVienID) {
        thanhVienRepository.deleteById(thanhVienID);
    }

    @Override
    public ThanhVien getThanhVienById(int thanhVienID) {
        ThanhVien thanhVien = thanhVienRepository.findById(thanhVienID).orElse(null);
        if (thanhVien != null) {
            return thanhVien;
        } else {
            return null;
        }   
    }
    
    
    
    
}
