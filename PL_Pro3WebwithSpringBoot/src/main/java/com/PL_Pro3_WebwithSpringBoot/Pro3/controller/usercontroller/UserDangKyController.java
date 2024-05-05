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
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author stsup
 */
@Controller
public class UserDangKyController {
    @Autowired
    private ThanhVienUserService thanhVienUserService;

    @GetMapping("/user/dangky")
    public String pageDangKy() {
        return "user/dangky";
    }
    
    @PostMapping("/user/dangky")
    public String dangKy(String maTV, String hoTen, String password, String confirmPassword, Model model) {
        String message = "";
        
        if (!password.equals(confirmPassword)) {
            message="Xác nhận mật khẩu không chính xác!";
            model.addAttribute("message", message);
            return "user/dangky";
        }
        
        if (thanhVienUserService.getThanhVienById(Integer.parseInt(maTV))!=null) {
            message="Mã thành viên đã có người sử dụng!";
            model.addAttribute("message", message);
            return "user/dangky";
        }
        
        ThanhVien thanhVien = new ThanhVien();
        thanhVien.setMaTV(Integer.parseInt(maTV));
        thanhVien.setHoTen(hoTen);
        thanhVien.setPassword(password);
        thanhVienUserService.addOrUpdateThanhVien(thanhVien);
        
        message="Đăng ký thành công.";
        
        model.addAttribute("message", message);
        model.addAttribute("thanhVien", thanhVien);
        
        return "user/dangnhap";
    }
}
