/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThanhVienAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AdminThanhVienController {
    private ThanhVienAdminService thanhVienAdminService;
    
    @Autowired
    public AdminThanhVienController(ThanhVienAdminService thanhVienAdminService){
        this.thanhVienAdminService = thanhVienAdminService;
    }
    
    
    
    
}
