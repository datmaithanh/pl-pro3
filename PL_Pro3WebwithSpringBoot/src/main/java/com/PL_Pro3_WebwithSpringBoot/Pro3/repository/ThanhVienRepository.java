/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Lenovo
 */

public interface ThanhVienRepository extends JpaRepository<ThanhVien, Object>{
    ThanhVien findByMaTVAndPassword(int maTV, String password);
    Optional<ThanhVien> findByMaTV(int maTV);
    
    @Query("SELECT tv FROM ThanhVien tv WHERE tv.email = :email")
    List<ThanhVien> findByEmail(String email);
    
    @Query("SELECT tv FROM ThanhVien tv WHERE tv.sdt = :sdt")
    List<ThanhVien> findBySDT(String sdt);

    @Query("SELECT DISTINCT nganh FROM ThanhVien")
    List<String> getAllNganh();
    
    @Query("SELECT DISTINCT khoa FROM ThanhVien")
    List<String> getAllKhoa();
}
