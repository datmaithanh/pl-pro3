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
public class ThietBiDTO {
    @NotEmpty(message = "MaTB không thể bỏ trống")
    private int maTB;
    @NotEmpty(message = "Tên TB không thể bỏ trống")
    private String tenTB;
    @NotEmpty(message = "Mô tả TB không thể bỏ trống")
    private String moTaTB;
    
    public ThietBiDTO () {}
    public ThietBiDTO(int maTB, String tenTB, String moTaTB){
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTaTB = moTaTB;
    }
            
}
