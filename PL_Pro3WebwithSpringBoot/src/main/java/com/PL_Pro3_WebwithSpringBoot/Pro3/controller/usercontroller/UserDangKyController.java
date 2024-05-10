/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThanhVienUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stsup
 */
@Controller
public class UserDangKyController {
    @Autowired
    private ThanhVienUserService thanhVienUserService;
    
    @GetMapping("/user/dangky")
    public String trangDangKy() {
        return "user/dangky";
    }
    
    @PostMapping("/user/dangky")
    public String dangKy(@RequestParam("maTV") int maTV, 
            @RequestParam("hoTen") String hoTen, 
            @RequestParam("khoa") String khoa, 
            @RequestParam("nganh") String nganh, 
            @RequestParam("sdt") String sdt, 
            @RequestParam("email") String email, 
            @RequestParam("password") String password, 
            @RequestParam("confirmPassword") String confirmPassword, 
            Model model) {
        
        if (thanhVienUserService.getThanhVienById(maTV)!=null) {
            model.addAttribute("error", "Mã thành viên đã có người sử dụng!");
            return "user/dangky";
        }
        
        ThanhVien thanhVien = new ThanhVien();
        thanhVien.setMaTV(maTV);
        thanhVien.setHoTen(hoTen);
        if (!khoa.isBlank())
            thanhVien.setKhoa(khoa);
        if (!nganh.isBlank())
            thanhVien.setNganh(nganh);
        if (!sdt.isBlank())
            thanhVien.setSdt(sdt);
        if (!email.isBlank())
            thanhVien.setEmail(email);
        thanhVien.setPassword(password);
        thanhVienUserService.addOrUpdateThanhVien(thanhVien);

        model.addAttribute("thanhVien", thanhVien);
        
        return "user/dangnhap";
    }
}
