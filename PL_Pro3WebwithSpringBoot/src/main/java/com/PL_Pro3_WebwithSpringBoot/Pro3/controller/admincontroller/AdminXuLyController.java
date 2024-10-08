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
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThanhVienAdminService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.data.repository.query.Param;
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
    private ThanhVienAdminService thanhVienAdminService;

    @Autowired
    public AdminXuLyController(XuLyAdminService xuLyAdminService, ThanhVienAdminService thanhVienAdminService) {
        this.xuLyAdminService = xuLyAdminService;
        this.thanhVienAdminService = thanhVienAdminService;
    }

    @GetMapping("/admin/xulyvipham")
    public String index(Model model) {
        List<XuLyDTO> xuLys = xuLyAdminService.getAllXuLy();
        model.addAttribute("xuLys", xuLys);
        List<ThanhVienDTO> thanhViens = thanhVienAdminService.getAllThanhVien();
        model.addAttribute("thanhViens", thanhViens);
        return "/admin/xulyvipham";
    }

    @RequestMapping("/admin/searchMaTV")
    public String viewHomePage(Model model, @Param("maTV") int maTV) {
        if (maTV != 0) {
            List<XuLy> listXuLy = xuLyAdminService.getXuLyByMaTV(maTV);
            List<ThanhVienDTO> thanhViens = thanhVienAdminService.getAllThanhVien();
            model.addAttribute("thanhViens", thanhViens);
            model.addAttribute("xuLys", listXuLy);
            model.addAttribute("maTV", maTV);
        } else {
            List<XuLyDTO> xuLys = xuLyAdminService.getAllXuLy();
            model.addAttribute("xuLys", xuLys);
            List<ThanhVienDTO> thanhViens = thanhVienAdminService.getAllThanhVien();
            model.addAttribute("thanhViens", thanhViens);
        }
        return "/admin/xulyvipham";
    }

    @GetMapping("/admin/themxulyvipham")
    public String create(Model model) {
        List<XuLyDTO> xuLys = xuLyAdminService.getAllXuLy();
        model.addAttribute("xuLys", xuLys);
        List<ThanhVienDTO> thanhViens = thanhVienAdminService.getAllThanhVien();
        model.addAttribute("thanhViens", thanhViens);

        return "/admin/themxulyvipham";
    }

    @GetMapping("/admin/store")
    public String addXuLy(@ModelAttribute XuLyDTO xuLyDTO, @RequestParam("maTV") int maTV) {
        if (maTV != 0) {
            xuLyAdminService.AddXuLy(maTV, xuLyDTO);
            return "redirect:/admin/xulyvipham";  
        } else {
            return "redirect:/admin/themxulyvipham";
        }
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        xuLyAdminService.deleteXuLyByID(id);
        return "redirect:/admin/xulyvipham";
    }

    @GetMapping("/admin/chitietvipham/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("xuLy", xuLyAdminService.getXuLyByID(id));
        return "/admin/chitietvipham";
    }

    @GetMapping("/admin/update/{id}")
    public String update(@ModelAttribute XuLyDTO xuLyDTO, @PathVariable("id") int id) {
        xuLyAdminService.updateXuLy(id, xuLyDTO);
        return "redirect:/admin/xulyvipham";
    }
}
