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
@Table(name = "xuly")
public class XuLy implements Serializable {
//    @Id
//    @Column(name = "MaXL")
//    private int maXL;
//    
//    @ManyToOne
//    @JoinColumn(name = "MaTV")
//    private ThanhVien thanhVien;
//    
//    @Column(name = "HinhThucXL")
//    private String hinhThucXL;
//    
//    @Column(name = "SoTien")
//    private int soTien;
//    
//    @CreationTimestamp
//    @Column(name = "NgayXL")
//    private LocalDateTime ngayXL;
//    
//    @Column(name = "TrangThaiXL")
//    private boolean trangThaiXL;

    
    
      @Id
    @Column(name = "maxl")
    private Integer maXL;

    @OneToOne
    @JoinColumn(name = "matv", nullable = false)
    private ThanhVien thanhVien;
       


    @Column(name = "hinhthucxl", length = 250)
    private String hinhThucXL;

    @Column(name = "sotien")
    private Integer soTien;

    @CreationTimestamp
    @Column(name = "ngayxl")
    private LocalDateTime ngayXL;

    @Column(name = "trangthaixl")
    private Boolean trangThaiXL; 
}
