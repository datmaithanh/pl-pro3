/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impladmin;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThongTinSDDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author agond
 */
public class ThongTinSDFilterSpecification {
    public static Specification<ThongTinSD> thoiGianVao(LocalDate ngayBatDau, LocalDate ngayKetThuc, String khoa, String nganh){
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();
            if (ngayBatDau!=null && ngayKetThuc!=null) {
                predicate = builder.and(predicate, builder.between(root.get("tgVao"), ngayBatDau, ngayKetThuc));
            }
            
            Join<ThongTinSD, ThanhVien> thanhVienJoin = root.join("thanhVien", JoinType.INNER);
            if (!khoa.equals("--") && khoa!=null && khoa!="" && !khoa.equals("")) {             
                predicate = builder.and(predicate, builder.equal(thanhVienJoin.get("khoa"), khoa));
            }
            if (!nganh.equals("--") && nganh!=null && nganh!="" && !nganh.equals("")) {
                predicate = builder.and(predicate, builder.equal(thanhVienJoin.get("nganh"), nganh));
            }
            return predicate;
        };
    }
    
    public static Specification<ThongTinSD> thoiGianMuon(String tenThietBi, LocalDate tgMuonTu, LocalDate tgMuonDen){
        return ( root, query, builder) -> {
            Predicate predicate = builder.conjunction();
            
            Join<ThongTinSD, ThietBi> thietBiJoin = root.join("thietBi", JoinType.INNER);
            if (tenThietBi != null && !tenThietBi.equals("")) {
                predicate = builder.and(predicate, builder.like(thietBiJoin.get("tenTB"), "%"+tenThietBi+"%"));
            }
            
            if (tgMuonTu!=null && tgMuonDen!=null) {
                predicate = builder.and(predicate, builder.between(root.get("tgMuon"), tgMuonTu, tgMuonDen));
            }
            return predicate;
        };
    }
    
}
