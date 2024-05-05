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
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "thietbi")
public class ThietBi implements Serializable {
    @Id
    @Column(name = "MaTB")
    private int maTB;
    @Column(name = "TenTB")
    private String tenTB;
    @Column(name = "motatb")
    private String moTaTB;
    
    @OneToMany(mappedBy = "thietBi", cascade = CascadeType.REMOVE)
    private Set<ThongTinSD> thongTinSDs = new HashSet<>();

}
