/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThietBiAdminService;
import java.util.List;
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
public class AdminThietBiController {
    private ThietBiAdminService thietBiAdminService;
    
    @Autowired
    public AdminThietBiController(ThietBiAdminService thietBiAdminService){
        this.thietBiAdminService = thietBiAdminService;
    }
    
    @GetMapping("/admin/thietbi") // Xử lý yêu cầu GET trên đường dẫn "/admin/thietbi"
    public String Thietbi(Model model) {
        List<ThietBiDTO> list = thietBiAdminService.getAllThietBiDTO();
        model.addAttribute("list", list);
        return "admin/thietbi";
    }
    
    
     @GetMapping("/admin/adddevice") // Xử lý yêu cầu GET trên đường dẫn "/admin/adddevice"
    public String addThietbipage(Model model) {
        List<ThietBiDTO> list = thietBiAdminService.getAllThietBiDTO();
        model.addAttribute("list", list);
        return "admin/adddevice";
    }
    @GetMapping("/admin/deletedevice") // Xử lý yêu cầu GET trên đường dẫn "/admin/deletedevice"
    public String DeleteThietbipage(Model model) {
        List<ThietBiDTO> list = thietBiAdminService.getAllThietBiDTO();
        model.addAttribute("list", list);
        return "admin/deletedevice";
    }
    @GetMapping("/admin/deletedevice_result")
    public String deleteThietBi(@RequestParam("maTB") int maTB) {
        thietBiAdminService.deleteThietBiByID(maTB);
        return "redirect:/admin/thanhvien";
    }
}
