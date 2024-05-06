/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impluser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.XuLyRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.XuLyUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class XuLyUserServiceImpl implements XuLyUserService{
    @Autowired
    XuLyRepository repository;

    @Override
    public List<XuLy> findByThanhVien(ThanhVien tv) {
        return repository.findByThanhVien(tv);
    }
}
