/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.dto;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThongTinSDDTO {
    @NotEmpty(message = "MaTT không thể bỏ trống")   
    private int maTT;
       
    private ThanhVien thanhVien;
    
    private ThietBi thietBi;
    
    private LocalDateTime tgVao;
   
    private LocalDateTime tgMuon;
    
    private LocalDateTime tgTra;
  
    private LocalDateTime tgDatCho;
    
    public ThongTinSDDTO(ThanhVien thanhVien, LocalDateTime tgVao) {
        this.thanhVien = thanhVien;
        this.tgVao = tgVao;
        this.thietBi = null;
        this.tgMuon = null;
        this.tgTra = null;
        this.tgDatCho = null;  
    }

    public ThongTinSDDTO(ThanhVien thanhVien, ThietBi thietBi, LocalDateTime tgVao, LocalDateTime tgMuon, LocalDateTime tgTra, LocalDateTime tgDatCho) {
        this.thanhVien = thanhVien;
        this.thietBi = thietBi;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
        this.tgDatCho = tgDatCho;
    }

    
    
    
    
}
