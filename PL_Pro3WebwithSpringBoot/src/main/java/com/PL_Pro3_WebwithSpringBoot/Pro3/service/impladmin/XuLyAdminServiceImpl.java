/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impladmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.XuLyDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.XuLyRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.XuLyAdminService;
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
public class XuLyAdminServiceImpl implements XuLyAdminService {

    private XuLyRepository xuLyRepository;
    private ThanhVienRepository thanhVienRepository;

    @Autowired
    public XuLyAdminServiceImpl(XuLyRepository xuLyRepository, ThanhVienRepository thanhVienRepository) {
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
        return xuLys.stream().map((xuly -> mapToXuLyDTO(xuly))).collect(Collectors.toList());
    }

    private XuLyDTO mapToXuLyDTO(XuLy xuLy) {
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
    public List<ThanhVienDTO> getAllThanhVien() {
        List<ThanhVien> thanhViens = thanhVienRepository.findAll();

        return thanhViens.stream().map((club -> mapToThanhVienDTO(club))).collect(Collectors.toList());
    }

    private ThanhVienDTO mapToThanhVienDTO(ThanhVien thanhVien) {
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
    public XuLyDTO getXuLyByID(int xuLyID) {
        XuLy xuLy = xuLyRepository.findById(xuLyID).get();
        return mapToXuLyDTO(xuLy);
    }

    @Override
    public List<XuLy> getXuLyByMaTV(int maTV) {
        List<XuLy> xuLys = xuLyRepository.findByThanhVien_MaTV(maTV);
        return xuLys;
    }

    private XuLy mapToXuLy(int maTV, XuLyDTO xuLyDTO) {

        ThanhVien thanhVien = thanhVienRepository.findById(maTV).get();
        int soTien = 0;
        if (xuLyDTO.getHinhThucXL() == "4") {
            soTien = xuLyDTO.getSoTien();
        } else {
            soTien = 0;
        }

        XuLy xuLy = XuLy.builder()
                .maXL(xuLyDTO.getMaXL())
                .thanhVien(thanhVien)
                .hinhThucXL(xuLyDTO.getHinhThucXL())
                .soTien(soTien)
                .ngayXL(xuLyDTO.getNgayXL())
                .trangThaiXL(xuLyDTO.getTrangThaiXL())
                .build();
        return xuLy;
    }

    public XuLy AddXuLy(int maTV, XuLyDTO xuLyDTO) {
        XuLy xuLy = mapToXuLy(maTV, xuLyDTO);
        return xuLyRepository.save(xuLy);

    }

    @Override
    public void deleteXuLyByID(int xuLyID) {
        xuLyRepository.deleteById(xuLyID);
    }

    @Override
    public void updateXuLy(int id, XuLyDTO xuLyDTO) {
        Optional<XuLy> optionalXuLy = xuLyRepository.findById(id);
        if (optionalXuLy.isPresent()) {
            XuLy existingXuLy = optionalXuLy.get();
            existingXuLy.setSoTien(xuLyDTO.getSoTien());
            existingXuLy.setHinhThucXL(xuLyDTO.getHinhThucXL());
            existingXuLy.setNgayXL(xuLyDTO.getNgayXL());
            existingXuLy.setTrangThaiXL(xuLyDTO.getTrangThaiXL());
            xuLyRepository.save(existingXuLy);

        }
    }

    @Override
    public List<XuLy> findByThanhVienMaTVTrueViPham(int maTV) {
        List<XuLy> xuLys = xuLyRepository.findByThanhVienMaTVTrueViPham(maTV);
        return xuLys;
    }

}
