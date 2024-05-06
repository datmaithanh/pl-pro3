/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Lenovo
 */
@Data
@Builder
public class ThanhVienDTO {

   
    
    @NotEmpty(message = "MaTV không thể bỏ trống")
    private int maTV;
    @NotEmpty(message = "Họ Tên không thể bỏ trống")
    private String hoTen;
    @NotEmpty(message = "Khoa không thể bỏ trống")
    private String khoa;
    @NotEmpty(message = "Ngành không thể bỏ trống")
    private String nganh;
    @NotEmpty(message = "SĐT không thể bỏ trống")
    private String sdt;
    @NotEmpty(message = "Email không thể bỏ trống")
    private String email;
    @NotEmpty(message = "Password không thể bỏ trống")
    private String password;

    public ThanhVienDTO() {
    }
    
    public ThanhVienDTO(int maTV, String hoTen, String khoa, String nganh, String sdt, String email, String password) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.khoa = khoa;
        this.nganh = nganh;
        this.sdt = sdt;
        this.email = email;
        this.password = password;
    }
}
