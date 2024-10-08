/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThongTinSDDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.XuLyDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThanhVienAdminService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThietBiAdminService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThongTinSDAdminService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.XuLyAdminService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author agond
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminThongkeController {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ThongTinSDAdminService thongTinSDAdminService;
    private ThanhVienAdminService thanhVienAdminService; 
    private ThietBiAdminService thietBiAdminService;
    private XuLyAdminService xuLyAdminService;

    
    @Autowired
    public AdminThongkeController(ThongTinSDAdminService thongTinSDAdminService, ThanhVienAdminService thanhVienAdminService, ThietBiAdminService thietBiAdminService, XuLyAdminService xuLyAdminService){
        this.thongTinSDAdminService = thongTinSDAdminService;
        this.thanhVienAdminService = thanhVienAdminService;
        this.thietBiAdminService = thietBiAdminService;
        this.xuLyAdminService = xuLyAdminService;
    }

    
    @GetMapping("/thongke-soluongvaoKHT")
    public String vaoThongKeSLvaoKHT (Model model)
    {
        List<ThongTinSDDTO> allThongtin = thongTinSDAdminService.getAllThongTinSD();
        model.addAttribute("history", allThongtin);        
        List<String> khoa = thanhVienAdminService.getAllKhoa();        
        model.addAttribute("dsKhoa", khoa);
        List<String> nganh = thanhVienAdminService.getAllNganh();
        model.addAttribute("dsNganh", nganh);

        return "admin/thongke-soluongvaoKHT";
    }   
    
    @PostMapping("/thongke-soluongvaoKHT")
    public String thongKeSLvaoKHT(Model model, 
            @RequestParam("ngayBatDau") String ngayBatDau,
            @RequestParam("ngayKetThuc") String ngayKetThuc,
            @RequestParam("khoa") String khoaDuocChon,
            @RequestParam("nganh") String nganhDuocChon)
    {        
        List<String> dskhoa = thanhVienAdminService.getAllKhoa();        
        model.addAttribute("dsKhoa", dskhoa);
        List<String> dsnganh = thanhVienAdminService.getAllNganh();
        model.addAttribute("dsNganh", dsnganh);
        
//        Pattern regexPattern = Pattern.compile("^(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])-(\\d{4})$");
//        Matcher ngayBatDauMatcher = regexPattern.matcher(ngayBatDau);
//        Matcher ngayKetThucMatcher = regexPattern.matcher(ngayKetThuc);
        
//        System.out.println("|||||||||||" + ngayBatDau);
//        System.out.println("|||||||||||" + ngayKetThuc);
        
        LocalDate fromDateTime = null, toDateTime =null;
        if (ngayBatDau.length()>0 && ngayKetThuc.length()>0) {
            fromDateTime = LocalDate.parse(ngayBatDau.trim(), formatter);
            toDateTime = LocalDate.parse(ngayKetThuc.trim(), formatter);
        }        
        
        List<ThongTinSDDTO> allThongtin = thongTinSDAdminService.filterVaoKHT(fromDateTime, toDateTime, khoaDuocChon, nganhDuocChon);
        model.addAttribute("history", allThongtin);   
        
        return "admin/thongke-soluongvaoKHT";
    }
    
    @GetMapping("/thongke-thietbi")
    public String vaoThongKeThietBiDuocMuon(Model model){
        List<ThongTinSDDTO> allThongtin = thongTinSDAdminService.getAllThongTinSD();
        model.addAttribute("historyThietBi", allThongtin);        
        return "admin/thongke-thietbi";
    }
    
    @PostMapping("/thongke-thietbi")
    public String thongKeThietBi(Model model,
            @RequestParam("tenThietBi") String tenThietBi,
            @RequestParam("tgMuonTu") String tgMuonTu,
            @RequestParam("tgMuonDen") String tgMuonDen)
    {        
        LocalDate fromDateTime = null, toDateTime =null;
        if (tgMuonTu.length()>0 && tgMuonDen.length()>0) {
            fromDateTime = LocalDate.parse(tgMuonTu.trim(), formatter);
            toDateTime = LocalDate.parse(tgMuonDen.trim(), formatter);
        } 
        
        List<ThongTinSDDTO> allThongtin = thongTinSDAdminService.filterMuonTB(tenThietBi, fromDateTime, toDateTime);
        model.addAttribute("historyThietBi", allThongtin);   
        
        return "admin/thongke-thietbi";
    }
    // @GetMapping("/thongke-vipham")
    // public String thongKeViPham(Model model) {
        
    //     return "admin/thongke-vipham";
    // }
    @GetMapping("/thongke-vipham")
    public String vaoThongKeSLvaoVP (Model model) {
        List<XuLyDTO> xuLys = xuLyAdminService.getAllXuLy();
       
        model.addAttribute("xuLys", xuLys);

         // Tính tổng số tiền bồi thường cho các mục có trạng thái xử lý là đã xử lý
        //  double totalCompensation = xuLys.stream()
        //  .filter(XuLyDTO::getTrangThaiXL) // Chỉ lấy các mục đã xử lý
        //  .mapToDouble(XuLyDTO::getSoTien) // Lấy số tiền bồi thường
        //  .sum();

        // model.addAttribute("totalCompensation", totalCompensation); // Đặt biến vào model
        List<XuLyDTO> xuLystt = xuLyAdminService.getAllXuLy().stream()
                                      .filter(xuLy -> xuLy.getTrangThaiXL())
                                      .collect(Collectors.toList());

        // Tính tổng bồi thường
        int tongBoiThuong = xuLystt.stream()
        .filter(xuLy -> xuLy.getSoTien() != null) // Lọc ra các đối tượng có giá trị soTien không null
        .mapToInt(XuLyDTO::getSoTien)
        .sum();
        // Đưa tổng bồi thường vào model để hiển thị trên giao diện
        model.addAttribute("tongBoiThuong", tongBoiThuong);
        return "admin/thongke-vipham";
}   
    
    // @PostMapping("/thongke-vipham")
    // public String thongKeSLvaoKVP(Model model)
    // {        
    //     // List<ThongTinSDDTO> allThongtin = thongTinSDAdminService.filterVaoKHT(fromDateTime, toDateTime, khoaDuocChon, nganhDuocChon);
    //     // model.addAttribute("history", allThongtin);   
        
    //     return "admin/thongke-vipham";
    // }
    
}
