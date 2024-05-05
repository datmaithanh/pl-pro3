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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stsup
 */
@Controller
public class UserThanhVienController {
    @Autowired
    private ThanhVienUserService thanhVienUserService;
    
    @GetMapping("/user/testdoimatkhau")
    public String testDoiMatKhauPage() {
        return "user/testDoimatkhau";
    }
    
    @GetMapping("/user/doimatkhau")
    public String doiMatKhauPage() {
        // Trả về giao diện để hiển thị form thứ hai
        return "user/doimatkhau";
    }
    
    @PostMapping("/user/testdoimatkhau")
    public String testDoiMatKhau (String maTV, Model model){
        model.addAttribute("maTV", maTV);
        return "user/doimatkhau";
    }
    
    @PostMapping("/user/doimatkhau")
    public String doiMatKhau (@RequestParam(name="maTV") String maTV, String matKhauCu, String matKhauMoi, String xacNhanMatKhauMoi, Model model){
        String message="";
        System.out.print(maTV);
        ThanhVien thanhVien = thanhVienUserService.getThanhVienById(Integer.parseInt(maTV));
        if(!matKhauCu.equals(thanhVien.getPassword())) {
            message="Mật khẩu hiện tại không chính xác!!";
            model.addAttribute("message", message);
            model.addAttribute("maTV", maTV);
            return "user/doimatkhau";
        }
        if(matKhauCu.equals(matKhauMoi)) {
            message="Mật khẩu mới không được trùng với mật khẩu cũ!";     
            model.addAttribute("message", message);
            model.addAttribute("maTV", maTV);
            return "user/doimatkhau";
        }
        if(!matKhauMoi.equals(xacNhanMatKhauMoi)) {
            message="Xác nhận mật khẩu mới không chính xác!";
            model.addAttribute("message", message);
            model.addAttribute("maTV", maTV);
            return "user/doimatkhau";
        }
        
        else {
            thanhVien.setPassword(matKhauMoi);
            thanhVienUserService.addOrUpdateThanhVien(thanhVien);
            message="Đổi mật khẩu thành công.";
        }
        model.addAttribute("message", message);
        return "user/testdoimatkhau";
    }
}
