package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import java.util.List;
import java.util.Optional;

@Repository
public interface ThanhVienRepository extends JpaRepository<ThanhVien, Integer> {
    // Các phương thức truy vấn dữ liệu khác

}