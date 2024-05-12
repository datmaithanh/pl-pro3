/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "thongtinsd")
public class ThongTinSD implements Serializable {
    @Id
    @Column(name = "MaTT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTT;
    @ManyToOne
    @JoinColumn(name = "maTV")
    private ThanhVien thanhVien;
    
    
    @ManyToOne
    @JoinColumn(name = "maTB")
    private ThietBi thietBi;
    
    @Column(name = "TGVao")
    private LocalDateTime tgVao;
    
    @Column(name = "TGMuon")
    private LocalDateTime tgMuon;
    
    @Column(name = "TGTra")
    private LocalDateTime tgTra;
    
    @Column(name = "tgdatcho")
    private LocalDateTime tgDatCho;

    

    

}
