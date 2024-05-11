package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; 

/**
 *
 * @author Lenovo
 */
@Repository
public interface ThongTinSDRepository extends JpaRepository<ThongTinSD, Integer> {
    Optional<ThongTinSD> findByThanhVien(ThanhVien thanhVien);
    Optional<ThongTinSD> findByMaTT(int maTT);
     
    ThongTinSD findByThietBi_MaTB(int maTB);
    
    @Query(value = "SELECT * FROM ThongTinSD t WHERE t.maTB = :maTB ORDER BY t.maTT DESC LIMIT 1", nativeQuery = true)
    ThongTinSD findByThietBiMax_MaTB(int maTB);
    
    
    @Query("SELECT sd " +
        "FROM ThongTinSD sd " +
        "WHERE sd.tgDatCho IS NOT NULL AND sd.tgMuon IS NULL AND CURRENT_TIMESTAMP - sd.tgDatCho <= 1 HOUR")
    List<ThongTinSD> findThongTinSDDaDatCho();


    // @Query("SELECT tv " +
    //         "FROM ThanhVien tv " +
    //         "WHERE tv.maTV IN (" +
    //                         "SELECT sd.thanhVien.maTV " +
    //                         "FROM ThongTinSD sd " +
    //                         "WHERE (sd.tgDatCho IS NOT NULL AND sd.tgMuon IS NULL AND CURRENT_TIMESTAMP - sd.tgDatCho <= 1 HOUR))" +
    //                         "AND (tv.maTV LIKE :maThanhVien)")

    @Query("SELECT sd " +
        "FROM ThongTinSD sd " +
        "WHERE (sd.tgDatCho IS NOT NULL AND sd.tgMuon IS NULL AND CURRENT_TIMESTAMP - sd.tgDatCho <= 1 HOUR)" +
        "AND (sd.thanhVien.maTV = :maThanhVien)")
    List<ThongTinSD> findThanhVienDatChoMuonThietBi(int maThanhVien);

}
