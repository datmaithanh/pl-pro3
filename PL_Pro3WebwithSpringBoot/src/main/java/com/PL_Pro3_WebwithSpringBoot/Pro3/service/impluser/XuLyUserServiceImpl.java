/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impluser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.XuLyUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.XuLyDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.XuLyRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.XuLyUserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class XuLyUserServiceImpl implements XuLyUserService{
    private XuLyRepository xuLyRepository;
    private ThanhVienRepository thanhVienRepository;
    
    @Autowired
    public XuLyUserServiceImpl(XuLyRepository xuLyRepository, ThanhVienRepository thanhVienRepository) {
        this.xuLyRepository = xuLyRepository;
        this.thanhVienRepository = thanhVienRepository;
    }
    
    
    @Override
    public boolean checkMaSoSVTrongXuLy(int maSoSinhVien) {
       List<XuLy> xuLyList = xuLyRepository.findByThanhVien_MaTV(maSoSinhVien);
        return !xuLyList.isEmpty();
    }

    @Override
    public List<XuLyDTO> getAllXuLy() {
        List<XuLy> xuLys = xuLyRepository.findAll();
        return xuLys.stream().map((xuly ->mapToXuLyDTO(xuly))).collect(Collectors.toList());
    }
    private  XuLyDTO mapToXuLyDTO(XuLy xuLy){
        XuLyDTO xuLyDTO = XuLyDTO.builder()
                .maXL(xuLy.getMaXL())
                .thanhVien(xuLy.getThanhVien())
                .hinhThucXL(xuLy.getHinhThucXL())
                .soTien(xuLy.getSoTien())
                .ngayXL(xuLy.getNgayXL())
                .trangThaiXL(xuLy.getTrangThaiXL())
                
                .build();

        return xuLyDTO;
    }

    @Override
    public XuLyDTO getXuLyByID(int xuLyID) {
        XuLy xuLy = xuLyRepository.findById(xuLyID).get();
        return mapToXuLyDTO(xuLy);
    }

    @Override
    public List<XuLy> getXuLyByMaTV(int maTV) {
        List<XuLy> xuLys = xuLyRepository.findByThanhVien_MaTV(maTV);
        return xuLys;
    }
}
