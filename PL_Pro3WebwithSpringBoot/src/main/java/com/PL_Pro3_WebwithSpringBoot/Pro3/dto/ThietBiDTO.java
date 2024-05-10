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
public class ThietBiDTO {
    @NotEmpty(message = "MaTB không thể bỏ trống")
    private int maTB;
    @NotEmpty(message = "Tên TB không thể bỏ trống")
    private String tenTB;
    @NotEmpty(message = "Mô tả TB không thể bỏ trống")
    private String moTaTB;           
}
