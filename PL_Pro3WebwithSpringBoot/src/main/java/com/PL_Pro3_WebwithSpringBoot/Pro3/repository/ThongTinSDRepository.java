package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;




/**
 *
 * @author Lenovo
 */
public interface ThongTinSDRepository extends JpaRepository<ThongTinSD, Object>, JpaSpecificationExecutor<ThongTinSD>{
    Optional<ThongTinSD> findByThanhVien(ThanhVien thanhVien);
    Optional<ThongTinSD> findByMaTT(int maTT);
     
    ThongTinSD findByThietBi_MaTB(int maTB);
    
    @Query(value = "SELECT * FROM ThongTinSD t WHERE t.maTB = :maTB ORDER BY t.maTT DESC LIMIT 1", nativeQuery = true)
    ThongTinSD findByThietBiMax_MaTB(int maTB);
    
    
    @Query("SELECT sd " +
        "FROM ThongTinSD sd " +
        "WHERE sd.tgDatCho IS NOT NULL AND sd.tgMuon IS NULL AND CURRENT_TIMESTAMP - sd.tgDatCho <= 1 HOUR")
    List<ThongTinSD> findThongTinSDDaDatCho();


    @Query("SELECT sd " +
        "FROM ThongTinSD sd " +
        "WHERE (sd.tgDatCho IS NOT NULL AND sd.tgMuon IS NULL AND CURRENT_TIMESTAMP - sd.tgDatCho <= 1 HOUR)" +
        "AND (sd.thanhVien.maTV = :maThanhVien)")
    List<ThongTinSD> findThanhVienDatChoMuonThietBi(int maThanhVien);
    

    @Query("SELECT sd " +
        "FROM ThongTinSD sd " +
        "WHERE (sd.tgMuon IS NOT NULL AND sd.tgTra IS NULL)" +
        "AND (sd.thanhVien.maTV = :maThanhVien)")
    List<ThongTinSD> findThanhVienDangMuonThietBi(int maThanhVien);
    
    @Query("SELECT sd " +
        "FROM ThongTinSD sd " +
        "WHERE (sd.tgMuon IS NOT NULL AND sd.tgTra IS NULL)")
    List<ThongTinSD> findThietBiCanTra();
}
