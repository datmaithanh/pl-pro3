/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.dto;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 *
 * @author Lenovo
 */
public class ThongTinSDDTO {
    @NotEmpty(message = "MaTT không thể bỏ trống")   
    private int maTT;
       
    private ThanhVien thanhVien;
    
    private ThietBi thietBi;
    
    private LocalDateTime tgVao;
   
    private LocalDateTime tgMuon;
    
    private LocalDateTime tgTra;
  
    private LocalDateTime tgDatCho;
}
