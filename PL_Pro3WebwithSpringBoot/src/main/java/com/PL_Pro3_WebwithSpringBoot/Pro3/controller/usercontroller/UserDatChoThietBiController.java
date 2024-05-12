/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThanhVienUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThietBiUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThongTinSDUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.XuLyUserService;
import jakarta.servlet.http.HttpSession;

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
    public String datChoMuonThietBi(Model model) {
        List<ThietBiDTO> thietBis = thietBiUserService.getThietBiNotInThongTinSDs();
        model.addAttribute("thietBis", thietBis);
        return "user/datchomuonthietbi";
    }

    @GetMapping("/user/huydatchomuonthietbi")
    public String datChoMuonThietBiTroVe() {
        return "user/index";
    }

    @GetMapping("/user/datchomuonthietbi/result")
    public String datChoMuonThietBiResult(@RequestParam("maThietBi") int maThietBi, Model model, HttpSession session) {
        model.addAttribute("currentTimeHour", LocalTime.now().getHour());
        model.addAttribute("currentTimeMinute", LocalTime.now().plusMinutes(1).getMinute());
        ThietBiDTO thietBiDTO = thietBiUserService.getThietBiDTOByID(maThietBi);
        ThanhVien thanhVien = (ThanhVien) session.getAttribute("thanhVien");
        model.addAttribute("thanhVien", thanhVien);
        model.addAttribute("thietBi", thietBiDTO);
        return "user/datchomuonthietbiresult";
    }

    @GetMapping("/user/datchomuonthietbi/resultwithmember")
    public String datChoMuonThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("thoiGianMuon") String thoiGianMuon,
            @RequestParam("gioMuon") String gioMuon, @RequestParam("maThanhVien") int maThanhVien, Model model, @RequestHeader(value = "referer", required = false) String referer) {
        ThietBiDTO thietBiDTO = thietBiUserService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi", thietBiDTO);
        ThanhVienDTO thanhVienDTO = thanhVienUserService.getThanhVienDTOById(maThanhVien);
        model.addAttribute("thanhVien", thanhVienDTO);

        List<String> messages = new ArrayList<>();
        boolean exists = true;
        // Kiểm tra các thông báo về việc xử lý của thành viên
        List<XuLy> xuLys = xuLyUserService.findByThanhVienMaTVTrueViPham(maThanhVien);
        if (xuLys != null && !xuLys.isEmpty()) {
            exists = xuLyUserService.checkMaSoSVTrongXuLy(maThanhVien);
            for (XuLy xuLy : xuLys) {
                int soTien = xuLy.getSoTien();
                if (soTien != 0) {
                    String message = "Mã số sinh viên " + xuLy.getThanhVien().getMaTV() + " " + xuLy.getHinhThucXL() + " với số tiền " + xuLy.getSoTien() + "đ, không được vào khu học tập";
                    messages.add(message);
                } else {
                    String message = "Mã số sinh viên " + xuLy.getThanhVien().getMaTV() + " " + xuLy.getHinhThucXL() + ", không được vào khu học tập";
                    messages.add(message);
                }
            }
            // Thêm danh sách thông báo và trạng thái vi phạm vào model
            model.addAttribute("messages", messages);
            model.addAttribute("coViPham", exists);
        } else {
            ThanhVien thanhVien = thanhVienUserService.getThanhVienById(maThanhVien);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime tgDatMuonFinal = LocalDateTime.parse(thoiGianMuon + "T" + gioMuon, formatter);
            ThietBi thietBi = thietBiUserService.getThietBiByID(maThietBi);
            ThongTinSDDTO thongTinSDDTO = new ThongTinSDDTO(thanhVien, thietBi, null, null, null, tgDatMuonFinal);
            thongTinSDUserService.addThongTinSD(thongTinSDDTO);
            String message = "Lưu ý: Vui lòng đến nhận thiết bị đúng giờ, nếu sau 1 giờ mà thiết bị vẫn chưa được nhận thì sẽ xóa đặt chổ!";
            messages.add(message);
            model.addAttribute("timeNgayDatChoMuon", thoiGianMuon);
            model.addAttribute("timeGioDatChoMuon", gioMuon);
            model.addAttribute("messages", messages);
        }
        model.addAttribute("maThanhVien", maThanhVien);

        return "user/datchomuonthietbiresultwithmember";

    }

    @GetMapping("/user/xemdatchomuonthietbi")
    public String xemDatChoMuon(HttpSession session, Model model) {

        ThanhVien thanhViens = (ThanhVien) session.getAttribute("thanhVien");
        List<ThongTinSDDTO> thanhVienthongTinSDDTOs = thongTinSDUserService.getThanhVienSDDaDatCho(thanhViens.getMaTV());

        if(thanhVienthongTinSDDTOs.isEmpty()){
            return "user/khongcothongtin";
        }
        else {
            model.addAttribute("thanhVienthongTinSDDTO", thanhVienthongTinSDDTOs);
            return "user/xemdatchomuonthietbi";
        }
    }

    @GetMapping("/user/xemdatchomuonthietbiresult")
    public String datChoMuonThietBiHuyLayThietBi(@RequestParam("maThietBi") int maThietBi, @RequestParam("maThanhVien") int maThanhVien, @RequestParam("thoiGianMuon") String thoiGianMuon, Model model) {
        ThietBiDTO thietBiDTO = thietBiUserService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi", thietBiDTO);
        ThanhVienDTO thanhVienDTO = thanhVienUserService.getThanhVienDTOById(maThanhVien);
        model.addAttribute("thanhVien", thanhVienDTO);

        model.addAttribute("thoiGianMuon", thoiGianMuon);
        return "user/xemdatchomuonthietbiresult";
    }

    @GetMapping("/user/xemdatchomuonthietbiresultfinal")
    public String datchomuonThietBiHuyLayThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("maThanhVien") int maThanhVien, Model model) {
        ThongTinSD thongTinSD = thongTinSDUserService.getThongTinSDByMaTB(maThietBi);
        model.addAttribute("thongTinSD", thongTinSD);

        List<String> messages = new ArrayList<>();
        boolean exists = true;

        exists = false;
        String message = "Mã số sinh viên " + maThanhVien + " hủy mượn thiết bị thành công!";
        thongTinSDUserService.deleteThongTinSDId(thongTinSD.getMaTT());

        model.addAttribute("coViPham", exists);
        model.addAttribute("messages", messages);
        model.addAttribute("maThanhVien", maThanhVien);

        return "user/xemdatchomuonthietbiresultfinal";
    }

    @GetMapping("/user/xemthietbidangmuon")
    public String xemThietBiDangMuon(HttpSession session, Model model) {
        
        ThanhVien thanhViens = (ThanhVien) session.getAttribute("thanhVien");
        List<ThongTinSDDTO> thanhVienMuonTBs = thongTinSDUserService.getThongTinSDDangMuonTB(thanhViens.getMaTV());

        if(thanhVienMuonTBs.isEmpty()){
            return "user/khongcothongtin";
        }
        else {
            model.addAttribute("thanhVienMuonTB", thanhVienMuonTBs);
            return "user/xemthietbidangmuon";
        }  
    }

}
