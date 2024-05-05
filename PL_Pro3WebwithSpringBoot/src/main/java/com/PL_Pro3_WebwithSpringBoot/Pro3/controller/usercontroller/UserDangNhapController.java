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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Lenovo
 */
@Controller
public class UserDangNhapController {
    @Autowired
    private ThanhVienUserService thanhVienUserService;
    
    @GetMapping("/user/dangnhap")
    public String pageDangNhap() {
        return "user/dangnhap";
    }

    @PostMapping("/user/dangnhap")
    public String dangNhap(String maTV, String password, RedirectAttributes redirectAttributes) {
        if (thanhVienUserService.kiemTraDangNhap(Integer.parseInt(maTV), password)) {
            ThanhVien thanhVien = thanhVienUserService.getThanhVienById(Integer.parseInt(maTV));
            System.out.print(thanhVien.getMaTV());
            return "redirect:/user?maTV=" + thanhVien.getMaTV(); // Điều hướng đến trang chủ
        } else {
            redirectAttributes.addFlashAttribute("error", "Thông tin đăng nhập không chính xác!");
            return "redirect:/user/dangnhap";
        }
    }
}
