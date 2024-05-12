    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impluser;


import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThietBiRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThietBiUserService;
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
public class ThietBiUserServiceImpl implements ThietBiUserService{
    private ThietBiRepository thietBiRepository;
    
    @Autowired
    ThietBiRepository repository;

    @Override
    public Optional<ThietBi> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<ThietBi> findAll() {
        return repository.findAll();
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
    public List<ThietBiDTO> getThietBiNotInThongTinSDs() {
        List<ThietBi> thietBis = thietBiRepository.findThietBiNotInThongTinSD();
        return thietBis.stream().map((thietBi ->mapToThietBiDTO(thietBi))).collect(Collectors.toList());
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
    public List<ThietBiDTO> getThietBiDaMuon() {
        List<ThietBi> thietBis = thietBiRepository.findThieBiDaMuon();
        return thietBis.stream().map((thietBi ->mapToThietBiDTO(thietBi))).collect(Collectors.toList());
    }

    @Override
    public List<ThietBiDTO> getSearchThietBiChuaMuon(String searchTerm){
        List<ThietBi> thietBis = thietBiRepository.searchThietBiNotInThongTinSD(searchTerm);
        return thietBis.stream().map((thietBi ->mapToThietBiDTO(thietBi))).collect(Collectors.toList());
    }
    
}
