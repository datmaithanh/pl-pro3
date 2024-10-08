/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Lenovo
 */
public interface XuLyRepository extends JpaRepository<XuLy, Object>{
    Optional<XuLy> findByMaXL(int maXL);
    List<XuLy> findByThanhVien_MaTV(int maTV);
    List<XuLy> findByThanhVien(ThanhVien tv);
    
    @Query("SELECT x FROM XuLy x WHERE x.thanhVien.maTV = :maTV AND x.trangThaiXL = false")
    List<XuLy> findByThanhVienMaTVTrueViPham(int maTV);
}
