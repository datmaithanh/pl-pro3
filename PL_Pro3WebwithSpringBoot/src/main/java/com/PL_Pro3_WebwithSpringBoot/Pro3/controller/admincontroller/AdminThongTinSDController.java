/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThongTinSDAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Lenovo
 */
@Controller
public class AdminThongTinSDController {
    private ThongTinSDAdminService thongTinSDAdminService;
    
    @Autowired
    public AdminThongTinSDController (ThongTinSDAdminService thongTinSDAdminService){
        this.thongTinSDAdminService = thongTinSDAdminService;
    }
}
