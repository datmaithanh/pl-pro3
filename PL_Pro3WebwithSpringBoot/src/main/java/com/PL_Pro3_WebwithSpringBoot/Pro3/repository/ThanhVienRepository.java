/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Lenovo
 */
public interface ThanhVienRepository extends JpaRepository<ThanhVien, Integer>{
     Optional<ThanhVien> findByHoTen(String url);
}
