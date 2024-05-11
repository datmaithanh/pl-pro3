/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Lenovo
 */
@Repository
public interface XuLyRepository extends JpaRepository<XuLy, Integer> {

    List<XuLy> findByThanhVien(ThanhVien tv);
    Optional<XuLy> findByMaXL(int maXL);
    List<XuLy> findByThanhVien_MaTV(int maTV);
}
