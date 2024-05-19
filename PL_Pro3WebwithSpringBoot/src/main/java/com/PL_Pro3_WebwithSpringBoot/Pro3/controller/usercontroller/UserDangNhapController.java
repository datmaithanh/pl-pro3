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
    
    private ThanhVienUserService thanhVienUserService;
    @Autowired
    public UserDangNhapController(ThanhVienUserService thanhVienUserService) {
        this.thanhVienUserService = thanhVienUserService;
    }
    
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
    
    @GetMapping("/user/quenmatkhau")
    public String quenMatKhau (){
        return "user/quenmatkhau";
    }
    
    @GetMapping("user/quenmatkhauresult")
    public String quenMatKhauResult(@RequestParam("emailtxt") String email, Model model, HttpSession session) {
        if (!thanhVienUserService.emailExists(email)) {
            model.addAttribute("error", "Email không tồn tại trong hệ thống.");
            return "user/quenmatkhau"; 
        }
        String otpString = thanhVienUserService.generateOtp();
        thanhVienUserService.sendOtpToEmail(email, otpString);

        
        session.setAttribute("otp", otpString);
        session.setAttribute("email", email);

        model.addAttribute("message", "OTP đã được gửi tới email của bạn.");
        return "user/quenmatkhauresult";
    }
    
    @PostMapping("/user/xacminhotp")
    public String verifyOtp(@RequestParam("otptxt") String otp, Model model, HttpSession session) {
        String sessionOtp = (String) session.getAttribute("otp");
        String email = (String) session.getAttribute("email");

        if (sessionOtp != null && sessionOtp.equals(otp)) {
            
            model.addAttribute("message", "OTP xác minh thành công. Bạn có thể đặt lại mật khẩu.");
            
            session.removeAttribute("otp");
            return "user/datlaimatkhaumoi"; // Chuyển đến trang đặt lại mật khẩu
        } else {
            
            model.addAttribute("error", "OTP không hợp lệ. Vui lòng thử lại.");
            return "user/quenmatkhauresult";
        }
    }
    
    @GetMapping("/user/resetpassword")
    public String resetPassword(@RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");

        if (email == null) {
            model.addAttribute("error", "Phiên đã hết hạn. Vui lòng thử lại.");
            return "user/quenmatkhau";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp. Vui lòng thử lại.");
            return "user/datlaimatkhau";
        }

        thanhVienUserService.updatePassword(email, newPassword);
        model.addAttribute("message", "Mật khẩu đã được đặt lại thành công. Vui lòng đăng nhập với mật khẩu mới.");
        
        session.removeAttribute("email");

        return "user/dangnhap";
    }



}
