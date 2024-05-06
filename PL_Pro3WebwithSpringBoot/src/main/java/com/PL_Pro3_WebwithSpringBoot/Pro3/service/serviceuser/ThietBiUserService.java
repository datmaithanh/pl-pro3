/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Lenovo
 */
public interface ThietBiUserService {
    public Optional<ThietBi> findById(Integer id);
    public List<ThietBi> findAll();
}
