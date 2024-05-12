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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maXL;

    @ManyToOne
    @JoinColumn(name = "maTV", nullable = false)
    private ThanhVien thanhVien;

    @Column(name = "hinhthucxl", length = 250)
    private String hinhThucXL;

    @Column(name = "sotien", nullable = false)
    private Integer soTien;

    @CreationTimestamp
    @Column(name = "NgayXL")
    private LocalDateTime ngayXL;

    @Column(name = "trangthaixl")
    private Boolean trangThaiXL; 
    
    
}