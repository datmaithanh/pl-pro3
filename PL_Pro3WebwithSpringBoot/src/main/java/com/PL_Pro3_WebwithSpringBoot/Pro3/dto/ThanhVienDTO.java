/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lenovo
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
