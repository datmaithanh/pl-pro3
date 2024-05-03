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
    public List<ThietBiDTO> getAllThietBiDTO() {
        List<ThietBi> thanhViens = thietBiRepository.findAll();
        return thanhViens.stream().map((club ->mapToThietBiDTO(club))).collect(Collectors.toList());
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
        ThietBi thietBi = mapToThanhVien(thanhVienDTO);
        return thanhVienRepository.save(thanhVien);
    }

    @Override
    public ThanhVienDTO getThanhVienById(int thanhVienID) {
        ThanhVien thanhVien = thanhVienRepository.findById(thanhVienID).get();
        return mapToThanhVienDTO(thanhVien);
    }

    @Override
    public void updateThanhVien(int id, ThanhVienDTO thanhVienDTO) {
        Optional<ThanhVien> optionalThanhVien = thanhVienRepository.findById(id);
        if (optionalThanhVien.isPresent()) {
            ThanhVien existingThanhVien = optionalThanhVien.get();
            // Cập nhật thông tin trong dòng hiện tại
            existingThanhVien.setMaTV(thanhVienDTO.getMaTV());
            existingThanhVien.setHoTen(thanhVienDTO.getHoTen());
            existingThanhVien.setKhoa(thanhVienDTO.getKhoa());
            existingThanhVien.setNganh(thanhVienDTO.getNganh());
            existingThanhVien.setSdt(thanhVienDTO.getSdt());
            existingThanhVien.setEmail(thanhVienDTO.getEmail());
            existingThanhVien.setPassword(thanhVienDTO.getPassword());

            thanhVienRepository.save(existingThanhVien);
        } else {
            // Xử lý khi không tìm thấy dòng cần cập nhật
        }
    }

    public void deleteThietBiByID(int thietBiID) {
        thietBiRepository.deleteById(thietBiID);
    }
    
    
}
