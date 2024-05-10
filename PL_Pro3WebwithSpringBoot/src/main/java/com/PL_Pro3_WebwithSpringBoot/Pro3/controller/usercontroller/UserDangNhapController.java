/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThanhVienUserService;
import jakarta.servlet.http.HttpSession;
import org.apache.el.stream.Optional;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author Lenovo
 */
@Controller
public class UserDangNhapController {
    public static ThanhVienDTO userLogin = new ThanhVienDTO();

    @Autowired
    private ThanhVienUserService thanhVienUserService;
    
    @GetMapping("/user/dangnhap")
    public String trangDangNhap() {
        return "user/dangnhap";
    }

    @PostMapping("/user/dangnhap")
    public String dangNhap(@RequestParam("maTV") int maTV, @RequestParam("password") String password, RedirectAttributes redirectAttributes, HttpSession session) {
        if (thanhVienUserService.kiemTraDangNhap(maTV, password)) {
            ThanhVien thanhVien = thanhVienUserService.getThanhVienById(maTV);
            session.setAttribute("thanhVien", thanhVien);
            return "redirect:/user/" + thanhVien.getMaTV();
        } else {
            redirectAttributes.addFlashAttribute("error", "Thông tin đăng nhập không chính xác!");
            return "redirect:/user/dangnhap";
        }
    }
}
