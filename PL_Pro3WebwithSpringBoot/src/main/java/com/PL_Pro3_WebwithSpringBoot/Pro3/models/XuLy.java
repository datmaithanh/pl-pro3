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
    @Id
    @Column(name = "MaXL")
    private int maXL;
    
    @ManyToOne
    @JoinColumn(name = "maTV")
    private ThanhVien thanhVien;
    
    @Column(name = "HinhThucXL")
    private String hinhThucXL;
    
    @Column(name = "SoTien")
    private Integer soTien;
    
    @CreationTimestamp
    @Column(name = "NgayXL")
    private LocalDateTime ngayXL;
    
    @Column(name = "TrangThaiXL")
    private boolean trangThaiXL;

}
