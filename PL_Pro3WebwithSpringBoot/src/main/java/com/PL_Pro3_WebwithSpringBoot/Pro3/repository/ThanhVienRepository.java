/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


/**
 *
 * @author Lenovo
 */
public interface ThanhVienRepository extends JpaRepository<ThanhVien, Object>{
     Optional<ThanhVien> findByMaTV(int url);
     ThanhVien findByMaTVAndPassword(int maTV, String password);
}
