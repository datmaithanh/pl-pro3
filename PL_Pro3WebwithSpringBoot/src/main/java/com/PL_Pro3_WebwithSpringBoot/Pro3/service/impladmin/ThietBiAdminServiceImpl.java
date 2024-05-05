/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impladmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThietBiRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThietBiAdminService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class ThietBiAdminServiceImpl implements ThietBiAdminService{   
    
    private ThietBiRepository thietBiRepository;
    
    @Autowired
    public ThietBiAdminServiceImpl(ThietBiRepository thietBiRepository) {
        this.thietBiRepository = thietBiRepository;
    }
    
    
    @Override
    public List<ThietBiDTO> getAllThietBiDTOs() {
        List<ThietBi> thietBis = thietBiRepository.findAll();
        return thietBis.stream().map((thietBi ->mapToThietBiDTO(thietBi))).collect(Collectors.toList());
    }
    
    private  ThietBiDTO mapToThietBiDTO(ThietBi thietBi){
        ThietBiDTO thietBiDTO = ThietBiDTO.builder()
                .maTB(thietBi.getMaTB())
                .tenTB(thietBi.getTenTB())
                .moTaTB(thietBi.getMoTaTB())
                
                .build();

        return thietBiDTO;
    }
    private ThietBi mapToThietBi(ThietBiDTO thietBiDTO) {
        ThietBi thietBi = ThietBi.builder()
                .maTB(thietBiDTO.getMaTB())
                .tenTB(thietBiDTO.getTenTB())
                .moTaTB(thietBiDTO.getMoTaTB())              
                .build();
        return thietBi;
    }

    @Override
    public ThietBi AddThietBi(ThietBiDTO thietBiDTO) {
//        Optional<ThanhVien> existingThanhVien = thanhVienRepository.findByMaTV(thanhVienDTO.getMaTV());
//        if (existingThanhVien.isPresent()) {
//            throw new RuntimeException("Mã thành viên đã tồn tại");
//        }
        ThietBi thietBi = mapToThietBi(thietBiDTO);
        return thietBiRepository.save(thietBi);
    }

    @Override
    public ThietBi getThietBiByID(int thietBiID) {
        ThietBi thietBi = thietBiRepository.findById(thietBiID).orElse(null);
        if (thietBi != null) {
            return thietBi;
        } else {
            return null;
        }   
    }

    @Override
    public ThietBiDTO getThietBiDTOByID(int thietBiID) {
        ThietBi thietBi = thietBiRepository.findById(thietBiID).orElse(null);
        if (thietBi != null) {
            return mapToThietBiDTO(thietBi);
        } else {
            return null;
        }
    }

    @Override
    public void updateThietBi(int id, ThietBiDTO thietBiDTO) {
        Optional<ThietBi> optionalThietBi = thietBiRepository.findById(id);
        if (optionalThietBi.isPresent()) {
            ThietBi existingThietBi = optionalThietBi.get();
            // Cập nhật thông tin trong dòng hiện tại
            existingThietBi.setMaTB(thietBiDTO.getMaTB());
            existingThietBi.setTenTB(thietBiDTO.getTenTB());
            existingThietBi.setMoTaTB(thietBiDTO.getMoTaTB());          

            thietBiRepository.save(existingThietBi);
        } else {
            // Xử lý khi không tìm thấy dòng cần cập nhật
        }
    }    

    @Override
    public void deleteThietBiByID(int thietBiID) {
        thietBiRepository.deleteById(thietBiID);
    }
      
    public List<ThietBiDTO> getThietBiNotInThongTinSDs() {
        List<ThietBi> thietBis = thietBiRepository.findThietBiNotInThongTinSD();
        return thietBis.stream().map((thietBi ->mapToThietBiDTO(thietBi))).collect(Collectors.toList());
    }

    @Override
    public List<ThietBiDTO> getThietBiDaMuon() {
        List<ThietBi> thietBis = thietBiRepository.findThieBiDaMuon();
        return thietBis.stream().map((thietBi ->mapToThietBiDTO(thietBi))).collect(Collectors.toList());
    }

    @Override
    public List<ThietBiDTO> getAllThietBi() {
        List<ThietBi> thanhViens = thietBiRepository.findAll();
        return thanhViens.stream().map((thietBi ->mapToThietBiDTO(thietBi))).collect(Collectors.toList());
    }
}
