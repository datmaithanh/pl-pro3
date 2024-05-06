package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; 

/**
 *
 * @author Lenovo
 */
@Repository
public interface ThongTinSDRepository extends JpaRepository<ThongTinSD, Integer> {
    Optional<ThongTinSD> findByThanhVien(ThanhVien thanhVien);
}
