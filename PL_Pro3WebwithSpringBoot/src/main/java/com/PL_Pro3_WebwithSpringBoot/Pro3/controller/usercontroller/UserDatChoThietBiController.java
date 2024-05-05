/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThongTinSDDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThietBiAdminService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThanhVienUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThietBiUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThongTinSDUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.XuLyUserService;

/**
 *
 * @author Lenovo
 */
@Controller
public class UserDatChoThietBiController {
    private ThietBiUserService thietBiUserService;
    private ThanhVienUserService thanhVienUserService;
    private ThongTinSDUserService thongTinSDUserService;
    private XuLyUserService xuLyUserService;
    @Autowired
    

    

    public UserDatChoThietBiController(ThietBiUserService thietBiUserService, ThanhVienUserService thanhVienUserService,
            ThongTinSDUserService thongTinSDUserService, XuLyUserService xuLyUserService) {
        this.thietBiUserService = thietBiUserService;
        this.thanhVienUserService = thanhVienUserService;
        this.thongTinSDUserService = thongTinSDUserService;
        this.xuLyUserService = xuLyUserService;
    }



    @GetMapping("/user/datchomuonthietbi")
    public String datChoMuonThietBi (Model model){
        List<ThietBiDTO> thietBis = thietBiUserService.getThietBiNotInThongTinSDs();
        model.addAttribute("thietBis", thietBis);
        return "user/datchomuonthietbi";
    }

    @GetMapping("/user/datchomuonthietbi/result")
    public String datChoMuonThietBiResult(@RequestParam("maThietBi") int maThietBi, Model model) {
        ThietBiDTO thietBiDTO = thietBiUserService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi",thietBiDTO);
        return "user/datchomuonthietbiresult";
    }

    @GetMapping("/user/huydatchomuonthietbi")
    public String huyDatCho(Model model){
        List<ThietBiDTO> thietBis = thietBiUserService.getThietBiNotInThongTinSDs();
        model.addAttribute("thietBis", thietBis);
        return "user/datchomuonthietbi";
    }

    @GetMapping("/user/datchomuonthietbi/resultwithmember")
    public String datChoMuonThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("maSoSinhVien") int maSoSinhVien, Model model, @RequestHeader(value = "referer", required = false) String referer) {
        ThietBiDTO thietBiDTO = thietBiUserService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi",thietBiDTO);
        ThanhVienDTO thanhVienDTO = thanhVienUserService.getThanhVienDTOById(maSoSinhVien);
        model.addAttribute("thanhVien",thanhVienDTO);
        
        
        
        List<String> messages = new ArrayList<>();
        boolean exists=true;
        // Kiểm tra nếu thành viên không tồn tại
        if (thanhVienDTO == null) {
            String message = "Mã số sinh viên " + maSoSinhVien + " chưa phải là thành viên!";
            exists = true;
            model.addAttribute("maSoSinhVien", maSoSinhVien);
            messages.add(message);
            model.addAttribute("coViPham", exists);
            model.addAttribute("messages", messages);
        } else {
            // Thành viên tồn tại, kiểm tra các thông báo về việc xử lý của thành viên
            List<XuLy> xuLys = xuLyUserService.getXuLyByMaTV(maSoSinhVien);
            if (xuLys != null && !xuLys.isEmpty()) {
                exists = xuLyUserService.checkMaSoSVTrongXuLy(maSoSinhVien);
                for (XuLy xuLy : xuLys) {
                    int soTien = xuLy.getSoTien();
                    if (soTien != 0) {
                        String message = "Mã số sinh viên " + xuLy.getThanhVien().getMaTV() + " " + xuLy.getHinhThucXL() + " với số tiền " + xuLy.getSoTien() +"đ, không được vào khu học tập";
                        messages.add(message);
                    } else {
                        String message = "Mã số sinh viên " + xuLy.getThanhVien().getMaTV() + " " + xuLy.getHinhThucXL()+", không được vào khu học tập";
                        messages.add(message);
                    }
                }
                // Thêm danh sách thông báo và trạng thái vi phạm vào model
                model.addAttribute("messages", messages);
                model.addAttribute("coViPham", exists);
            } else {
                ThanhVien thanhVien = thanhVienUserService.getThanhVienById(maSoSinhVien);
                LocalDateTime timeNow = LocalDateTime.now();
                ThietBi thietBi = thietBiUserService.getThietBiByID(maThietBi);
                ThongTinSDDTO thongTinSDDTO = new ThongTinSDDTO(thanhVien, thietBi, null, null, null, timeNow);
                thongTinSDUserService.addThongTinSD(thongTinSDDTO);
                String message = "Đặt chổ mượn thiết bị thành công!";
                messages.add(message);
                model.addAttribute("messages", messages);
            }
            model.addAttribute("maSoSinhVien", maSoSinhVien);
        }
        
        return "user/datchomuonthietbiresultwithmember";

    }
    

    @GetMapping("/user/datchomuonthietbi/resultSearch")
    public String timKiemThietBi(@RequestParam("searchTerm") String searchTerm , Model model) {
        List<ThietBiDTO> thietBiDTOs = thietBiUserService.getSearchThietBiChuaMuon(searchTerm);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("thietBis",thietBiDTOs);
        return "user/datchomuonthietbiresultSearch";
    }
}
