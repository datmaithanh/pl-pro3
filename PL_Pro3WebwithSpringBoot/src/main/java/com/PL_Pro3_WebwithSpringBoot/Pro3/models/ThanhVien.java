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
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "thanhvien")
public class ThanhVien implements Serializable {
    @Id
    @Column(name = "MaTV")
    private int maTV;
    @Column(name = "hoten")
    private String hoTen;
    @Column(name = "Khoa")
    private String khoa;
    @Column(name = "Nganh")
    private String nganh;
    @Column(name = "SDT")
    private String sdt;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    
    @OneToMany(mappedBy = "thanhVien" ,cascade =CascadeType.REMOVE )
    private Set<XuLy> xuLys = new HashSet<>();
}
