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
import org.apache.el.stream.Optional;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Lenovo
 */
@Controller
public class UserLoginController {
    public static ThanhVienDTO userLogin = new ThanhVienDTO();


    @Autowired
    private ThanhVienUserService tvSe;
    @PostMapping("checklogin")
    public String checklogin(Model m, @RequestParam("taiKhoan") String taiKhoan , @RequestParam("password") String password) {
        if (taiKhoan.equals("") && password.equals("")) {
            m.addAttribute("erroll", "Tài khoản và mật khẩu không được để trống");
            return "user/login";
        } else {
            List<ThanhVien> list = tvSe.findAll();
            for (ThanhVien us : list) {
                if (us.getEmail().equals(taiKhoan) && us.getPassword().equals(password)) {
                    System.out.println("Đăng nhập thành công !!");
                    userLogin.setMaTV(us.getMaTV());
                    System.out.println("userLogin: "+us.getMaTV());
//                    userLogin.setHoTen(us.getHoTen());
//                    userLogin.set(us.getMaTV());
//                    userLogin.setHoTen(us.getMaTV());
//                    userLogin.setMaTV(us.getMaTV());
//                    userLogin.setHoTen(us.getMaTV());
                    m.addAttribute("username", us.getHoTen());
                    m.addAttribute("maTV", userLogin.getMaTV());
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
