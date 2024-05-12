package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impluser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThongTinSDDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThongTinSDRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThongTinSDUserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThongTinSDUserServiceImpl implements ThongTinSDUserService {
    private ThongTinSDRepository thongTinSDRepository;
    
    @Autowired
    public ThongTinSDUserServiceImpl(ThongTinSDRepository thongTinSDRepository) {
        this.thongTinSDRepository = thongTinSDRepository;
    }

    @Override
    public Optional<ThongTinSD> findByThanhVien(ThanhVien thanhVien) {
        return thongTinSDRepository.findByThanhVien(thanhVien);
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

    @Override
    public List<ThongTinSDDTO> getThanhVienSDDaDatCho(int maThanhVien) {
        List<ThongTinSD> thongTinSDs = thongTinSDRepository.findThanhVienDatChoMuonThietBi(maThanhVien);
        return thongTinSDs.stream().map((thongTinSD ->mapToThongTinSD(thongTinSD))).collect(Collectors.toList());
    }

    @Override
    public List<ThongTinSDDTO> getThongTinSDDangMuonTB(int maThanhVien){
        List<ThongTinSD> thongTinSDs = thongTinSDRepository.findThanhVienDangMuonThietBi(maThanhVien);
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
    public void deleteThongTinSDId(int thongTinSDId) {
        thongTinSDRepository.deleteById(thongTinSDId);
    }
}
