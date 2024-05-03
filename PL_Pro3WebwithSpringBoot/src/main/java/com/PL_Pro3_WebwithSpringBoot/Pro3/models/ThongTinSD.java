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
import org.hibernate.annotations.CreationTimestamp;


import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "thongtinsd")
public class ThongTinSD implements Serializable {
    @Id
    @Column(name = "MaTT")
    private int maTT;
    @ManyToOne
    @JoinColumn(name = "maTV")
    private ThanhVien thanhVien;
    @ManyToOne
    @JoinColumn(name = "maTB")
    public ThietBi thietBi;
    @CreationTimestamp
    @Column(name = "TGVao")
    private LocalDateTime tgVao;
    @CreationTimestamp
    @Column(name = "TGMuon")
    private LocalDateTime tgMuon;
    @CreationTimestamp
    @Column(name = "TGTra")
    private LocalDateTime tgTra;
    @CreationTimestamp
    @Column(name = "TGDatCho")
    private LocalDateTime tgDatCho;
}
