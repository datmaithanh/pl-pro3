/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import java.util.Optional;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface ThanhVienUserService {

    public Optional<ThanhVien> findById(Integer id);
    public List<ThanhVien> findAll();
}
