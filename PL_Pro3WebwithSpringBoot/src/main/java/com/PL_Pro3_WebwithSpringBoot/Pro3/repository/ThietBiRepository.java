/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.repository;



import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Lenovo
 */
public interface ThietBiRepository extends JpaRepository<ThietBi, Integer>{
     Optional<ThietBi> findByTenTB(String url);
  

    @Query("SELECT tb " +
       "FROM ThietBi tb " +
       "WHERE tb.maTB NOT IN (" +
           "SELECT tts.thietBi.maTB " +
           "FROM ThongTinSD tts " +
           "WHERE (tts.tgMuon IS NOT NULL AND tts.tgTra IS NULL) OR " +
                 "(tts.tgMuon IS NULL AND tts.tgTra IS NULL AND tts.tgDatCho IS NOT NULL AND CURRENT_TIMESTAMP - tts.tgDatCho <= 1 HOUR)" +
       ")")
    List<ThietBi> findThietBiNotInThongTinSD();



    
    
    
    
    @Query("SELECT tb " +
       "FROM ThietBi tb " +
       "LEFT JOIN ThongTinSD sd ON tb.maTB = sd.thietBi.maTB " +
       "WHERE sd.tgMuon IS NOT NULL AND sd.tgTra IS NULL")
    List<ThietBi> findThieBiDaMuon();
   
    
    
    @Query("SELECT tb " +
       "FROM ThietBi tb " +
       "LEFT JOIN ThongTinSD sd ON tb.maTB = sd.thietBi.maTB " +
       "WHERE sd.tgMuon IS NOT NULL AND sd.tgTra IS NULL AND sd.tgDatCho IS NOT NULL")
    List<ThietBi> findThieBiDaDatCho();




    @Query("SELECT tb " +
    "FROM ThietBi tb " +
    "WHERE tb.maTB NOT IN (" +
        "SELECT tts.thietBi.maTB " +
        "FROM ThongTinSD tts " +
        "WHERE ((tts.tgMuon IS NOT NULL AND tts.tgTra IS NULL) OR " +
              "(tts.tgMuon IS NULL AND tts.tgTra IS NULL AND tts.tgDatCho IS NOT NULL AND CURRENT_TIMESTAMP - tts.tgDatCho <= 1 HOUR))" +
    ") AND (tb.tenTB LIKE %:searchTerm% OR tb.moTaTB LIKE %:searchTerm%) " 
    )

   List<ThietBi> searchThietBiNotInThongTinSD(String searchTerm);
    

}
