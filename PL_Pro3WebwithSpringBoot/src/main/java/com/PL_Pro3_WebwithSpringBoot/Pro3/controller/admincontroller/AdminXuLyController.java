/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.XuLyDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import org.springframework.web.bind.annotation.GetMapping;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.XuLyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lenovo
 */
@Controller
public class AdminXuLyController {

    private XuLyAdminService xuLyAdminService;

    @Autowired
    public AdminXuLyController(XuLyAdminService xuLyAdminService) {
        this.xuLyAdminService = xuLyAdminService;
    }

    @GetMapping("/admin/index")
    public String index(Model model) {
        List<XuLyDTO> xuLys = xuLyAdminService.getAllXuLy();
        model.addAttribute("xuLys", xuLys);
        return "/admin/index";
    }

    @GetMapping("/admin/create")
    public String create(Model model) {
        List<XuLyDTO> xuLys = xuLyAdminService.getAllXuLy();
        model.addAttribute("xuLys", xuLys);
          List<ThanhVienDTO> thanhViens = xuLyAdminService.getAllThanhVien();
        model.addAttribute("thanhViens", thanhViens);
        return "/admin/create";
    }

    @GetMapping("/admin/store")
    public String addXuLy(@ModelAttribute XuLyDTO xuLyDTO) {
        xuLyAdminService.AddXuLy(xuLyDTO);

//      System.out.println();
//        System.out.println(xuLyDTO.getThanhVien().getMaTV());
        // Optional: Redirect to confirmation page or display success message
//        xuLyAdminService.AddXuLy(xuLyDTO);
        return "/admin/index";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        xuLyAdminService.deleteXuLyByID(id);
        return "redirect:/admin/index";
    }

    @GetMapping("/admin/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("xuLy", xuLyAdminService.getXuLyByID(id));
        return "/admin/detail";
    }

    @GetMapping("/admin/update/{id}")
    public String update(@ModelAttribute XuLyDTO xuLyDTO, @PathVariable("id") int id) {
        xuLyAdminService.updateXuLy(id, xuLyDTO);
        return  "redirect:/admin/index";
//            return "/admin/index";
    }
}
