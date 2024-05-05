/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impladmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThongTinSDDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThongTinSDRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThongTinSDAdminService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class ThongTinSDAdminServiceImpl implements ThongTinSDAdminService{
    
    private ThongTinSDRepository thongTinSDRepository;
    
    @Autowired
    public ThongTinSDAdminServiceImpl(ThongTinSDRepository thongTinSDRepository) {
        this.thongTinSDRepository = thongTinSDRepository;
    }
    
    
    @Override
    public ThongTinSD addThongTinSD(ThongTinSDDTO thongTinSDDTO) {
        ThongTinSD thongTinSD = mapToThongTinSD(thongTinSDDTO);
        return thongTinSDRepository.save(thongTinSD);
    }
    
    private ThongTinSD mapToThongTinSD (ThongTinSDDTO thongTinSDDTO) {
        ThongTinSD thongTinSD = ThongTinSD.builder()
                .thanhVien(thongTinSDDTO.getThanhVien())
                .thietBi(thongTinSDDTO.getThietBi())
                .tgVao(thongTinSDDTO.getTgVao())
                .tgMuon(thongTinSDDTO.getTgMuon())
                .tgTra(thongTinSDDTO.getTgTra())
                .tgDatCho(thongTinSDDTO.getTgDatCho())
                .build();
        return thongTinSD;
    }    

    @Override
    public ThongTinSD getThongTinSDByMaTB(int maTB) {
        ThongTinSD thongTinSD = thongTinSDRepository.findByThietBiMax_MaTB(maTB);
        return thongTinSD;
    }

    @Override
    public void updateThongTinSD(ThongTinSD thongTinSD) {
        thongTinSDRepository.save(thongTinSD);
    }

    @Override
    public List<ThongTinSDDTO> getThongTinSDDaDatCho() {
        List<ThongTinSD> thongTinSDs = thongTinSDRepository.findThongTinSDDaDatCho();
        return thongTinSDs.stream().map((thongTinSD ->mapToThongTinSD(thongTinSD))).collect(Collectors.toList());
    }
    
    private  ThongTinSDDTO mapToThongTinSD(ThongTinSD thongTinSD){
        ThongTinSDDTO thongTinSDDTO = ThongTinSDDTO.builder()
                .maTT(thongTinSD.getMaTT())
                .thanhVien(thongTinSD.getThanhVien())
                .thietBi(thongTinSD.getThietBi())
                .tgVao(thongTinSD.getTgVao())
                .tgMuon(thongTinSD.getTgMuon())
                .tgTra(thongTinSD.getTgTra())
                .tgDatCho(thongTinSD.getTgDatCho())
                .build();

        return thongTinSDDTO;
    }

    @Override
    public List<ThongTinSDDTO> getAllThongTinSD() {
        List<ThongTinSD> thongTinSDs = thongTinSDRepository.findAll();
        return thongTinSDs.stream().map((thongTinSD ->mapToThongTinSD(thongTinSD))).collect(Collectors.toList());
    }

    @Override
    public List<ThongTinSDDTO> filterThongTinSD(LocalDate ngayBatDau, LocalDate ngayKetThuc, String khoa, String nganh){
        List<ThongTinSD> thongTinSDs = thongTinSDRepository.findAll(ThongTinSDFilterSpecification.withParams(ngayBatDau, ngayKetThuc, khoa, nganh));
        return thongTinSDs.stream().map((thongTinSD ->mapToThongTinSD(thongTinSD))).collect(Collectors.toList());
    }
    
}
