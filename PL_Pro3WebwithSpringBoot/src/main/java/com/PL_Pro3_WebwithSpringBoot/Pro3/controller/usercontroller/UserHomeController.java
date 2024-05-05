/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThanhVienUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lenovo
 */
@Controller
public class UserHomeController {
    @Autowired
    private ThanhVienUserService thanhVienUserService;
    
    @GetMapping("/user") 
    public String userPage(@RequestParam("maTV") int maTV, Model model) {
        ThanhVien thanhVien = thanhVienUserService.getThanhVienById(maTV);
        model.addAttribute("thanhVien", thanhVien);
        return "user/index"; 
    }
}
