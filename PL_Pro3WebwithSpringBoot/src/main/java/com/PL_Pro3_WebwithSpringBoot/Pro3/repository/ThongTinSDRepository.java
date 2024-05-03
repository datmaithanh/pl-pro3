/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *
 * @author Lenovo
 */
@Repository
public interface ThongTinSDRepository extends JpaRepository<ThongTinSD, Integer> {

    List<ThongTinSD> findByThanhVien(ThanhVien thanhVien);

//    List<ThongTinSD> findByThietBi(ThietBi thietBi);

}
