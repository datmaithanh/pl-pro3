/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.dto;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 *
 * @author Lenovo
 */
public class XuLyDTO {
    
    @NotEmpty(message = "MaXL không thể bỏ trống")
    private int maXL;   
    private ThanhVien thanhVien;
    @NotEmpty(message = "Hình thức XL không thể bỏ trống")
    private String hinhThucXL;  
    private int soTien;
    private LocalDateTime ngayXL;
    @NotEmpty(message = "Trạng thái XL không thể bỏ trống")
    private boolean trangThaiXL;
}
