/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Lenovo
 */
@Controller
public class UserLoginController {

    @Autowired
    private ThanhVienRepository tvRe;
    @PostMapping("checklogin")
    public String checklogin(Model m, @RequestParam("taiKhoan") String taiKhoan, @RequestParam("password") String password) {
        if (taiKhoan.equals("") && password.equals("")) {
            m.addAttribute("erroll", "Tài khoản và mật khẩu không được để trống");
            return "user/login";
        } else {
            Iterable<ThanhVien> list = tvRe.findAll();
            for (ThanhVien us : list) {
                if (us.getEmail().equals(taiKhoan) && us.getPassword().equals(password)) {
                    System.out.println("Đăng nhập thành công !!");
                    m.addAttribute("username", us.getHoTen());
                    m.addAttribute("maTV", us.getMaTV());
                    return "user/index";
                }
            }
            m.addAttribute("erroll", "Sai Tài khoản hoặc mật khẩu");
            return "user/login";
        }
    }
    
    @GetMapping("login")
    public String loginView(){
        return "user/login";
    }
}
