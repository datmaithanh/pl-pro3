/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThongTinSDDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.XuLyDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThanhVienAdminService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThietBiAdminService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThongTinSDAdminService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.XuLyAdminService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AdminThanhVienController {
    private ThanhVienAdminService thanhVienAdminService;
    private XuLyAdminService xuLyAdminService;
    private ThongTinSDAdminService thongTinSDAdminService;
    private ThietBiAdminService thietBiAdminService;
    
    @Autowired
    
    public AdminThanhVienController(ThanhVienAdminService thanhVienAdminService, XuLyAdminService xuLyAdminService,ThongTinSDAdminService thongTinSDAdminService, ThietBiAdminService thietBiAdminService ){
        this.thanhVienAdminService = thanhVienAdminService;
        this.xuLyAdminService = xuLyAdminService;
        this.thongTinSDAdminService = thongTinSDAdminService;
        this.thietBiAdminService = thietBiAdminService;
        
    }
    
    
    @GetMapping("/admin/kiemtravipham")
    public String kiemTraViPham (Model model){
        List<XuLyDTO> xuLys = xuLyAdminService.getAllXuLy();
        model.addAttribute("xuLys", xuLys);
        return "admin/kiemtravipham";
    }
    
    @GetMapping("/admin/kiemtravipham/result")
    public String kiemTraViPhamResult(@RequestParam("maSoSinhVien") int maSoSinhVien, Model model) {
        List<XuLyDTO> xuLyDTOs = xuLyAdminService.getAllXuLy();
        model.addAttribute("xuLys", xuLyDTOs);
        List<String> messages = new ArrayList<>();

        // Kiểm tra xem service có tồn tại không
        if (xuLyAdminService != null) {
            boolean exists = xuLyAdminService.checkMaSoSVTrongXuLy(maSoSinhVien);
            List<XuLy> xuLys = xuLyAdminService.getXuLyByMaTV(maSoSinhVien);
            ThanhVienDTO thanhVienDTO = thanhVienAdminService.getThanhVienDTOById(maSoSinhVien);

            // Kiểm tra xem sinh viên có phải là thành viên không
            if (thanhVienDTO == null) {
                String message = "Mã số sinh viên " + maSoSinhVien + " chưa phải là thành viên!!!";
                messages.add(message);
            } else {
                // Kiểm tra xem có thông tin xử lý nào cho sinh viên không
                if (xuLys != null && !xuLys.isEmpty()) {
                    for (XuLy xuLy : xuLys) {
                        int soTien = xuLy.getSoTien();
                        if (soTien != 0) {
                            String message = "Mã số sinh viên " + xuLy.getThanhVien().getMaTV() + " " + xuLy.getHinhThucXL() + " với số tiền " + xuLy.getSoTien()+"đ, không được vào khu học tập";
                            messages.add(message);
                        } else {
                            String message = "Mã số sinh viên " + xuLy.getThanhVien().getMaTV() + " " + xuLy.getHinhThucXL()+", không được vào khu học tập";
                            messages.add(message);
                        }
                    }
                }else{
                    // Sinh viên là thành viên và không có vi phạm
                    String message = "Mã sinh viên "+thanhVienDTO.getMaTV()+" không có vi phạm.";
                    messages.add(message);
                }
            }

            // Thêm danh sách thông báo và trạng thái vi phạm vào model
            model.addAttribute("messages", messages);
            model.addAttribute("coViPham", exists);
        }

        // Trả về view để hiển thị thông tin
        return "admin/kiemtravipham";
    }



    
    
    @GetMapping("/admin/vaokhuhoctap")
    public String vaoKhuHocTap (){
        
        return "admin/vaokhuhoctap";
    }
    
    @GetMapping("/admin/vaokhuhoctap/result")
    public String vaoKhuHocTapResult(@RequestParam("maSoSinhVien") int maSoSinhVien, Model model) {
        // Lấy danh sách thành viên và thông tin của thành viên theo mã số
        ThanhVienDTO thanhVienDTO = thanhVienAdminService.getThanhVienDTOById(maSoSinhVien);
        List<String> messages = new ArrayList<>();

        // Kiểm tra nếu thành viên không tồn tại
        if (thanhVienDTO == null) {
            String message = "Mã số sinh viên " + maSoSinhVien + " chưa phải là thành viên!";
            messages.add(message);
            model.addAttribute("messages", messages);
        } else {
            // Thành viên tồn tại, kiểm tra các thông báo về việc xử lý của thành viên
            List<XuLy> xuLys = xuLyAdminService.getXuLyByMaTV(maSoSinhVien);
            if (xuLys != null && !xuLys.isEmpty()) {
                boolean exists = xuLyAdminService.checkMaSoSVTrongXuLy(maSoSinhVien);
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
                ThanhVien thanhVien = thanhVienAdminService.getThanhVienById(maSoSinhVien);
                LocalDateTime timeNow = LocalDateTime.now();
                ThongTinSDDTO thongTinSDDTO = new ThongTinSDDTO(thanhVien, timeNow);
                thongTinSDAdminService.addThongTinSD(thongTinSDDTO);
                String message = "Mời vào!";
                messages.add(message);
                model.addAttribute("messages", messages);
            }
        }
        ThanhVienDTO thanhVienDTOs = thanhVienAdminService.getThanhVienDTOById(maSoSinhVien);
        model.addAttribute("thanhVienDTOs", thanhVienDTOs);
        // Trả về view và hiển thị thông tin tương ứng
        return "admin/vaokhuhoctap";
    }
    
    
    @GetMapping("/admin/muonthietbi")
    public String muonThietBi (Model model){
        List<ThietBiDTO> thietBis = thietBiAdminService.getThietBiNotInThongTinSDs();
        model.addAttribute("thietBis", thietBis);
        return "admin/muonthietbi";
    }
    
    
    @GetMapping("/admin/muonthietbi/result")
    public String muonThietBiResult(@RequestParam("maThietBi") int maThietBi, Model model) {
        ThietBiDTO thietBiDTO = thietBiAdminService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi",thietBiDTO);

        
        // Trả về view và hiển thị thông tin tương ứng
        return "admin/muonthietbiresult";
    }
    
    @GetMapping("/admin/muonthietbi/resultwithmember")
    public String muonThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("maSoSinhVien") int maSoSinhVien, Model model, @RequestHeader(value = "referer", required = false) String referer) {
        ThietBiDTO thietBiDTO = thietBiAdminService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi",thietBiDTO);
        ThanhVienDTO thanhVienDTO = thanhVienAdminService.getThanhVienDTOById(maSoSinhVien);
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
            List<XuLy> xuLys = xuLyAdminService.getXuLyByMaTV(maSoSinhVien);
            if (xuLys != null && !xuLys.isEmpty()) {
                exists = xuLyAdminService.checkMaSoSVTrongXuLy(maSoSinhVien);
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
                ThanhVien thanhVien = thanhVienAdminService.getThanhVienById(maSoSinhVien);
                LocalDateTime timeNow = LocalDateTime.now();
                ThietBi thietBi = thietBiAdminService.getThietBiByID(maThietBi);
                
                ThongTinSDDTO thongTinSDDTO = new ThongTinSDDTO( thanhVien, thietBi, null,timeNow , null,null );
                thongTinSDAdminService.addThongTinSD(thongTinSDDTO);
                String message = "Mượn thiết bị thành công!";
                messages.add(message);
                model.addAttribute("messages", messages);
            }
            model.addAttribute("maSoSinhVien", maSoSinhVien);
        }
        
        return "admin/muonthietbiresultwithmember";
    }
    
    
    
    @GetMapping("/admin/trathietbi")
    public String traThietBi (Model model){
        List<ThietBiDTO> thietBis = thietBiAdminService.getThietBiDaMuon();
        model.addAttribute("thietBis", thietBis);
        return "admin/trathietbi";
    }
    
    @GetMapping("/admin/trathietbi/result")
    public String traThietBiResult(@RequestParam("maThietBi") int maThietBi, Model model) {
        ThongTinSD thongTinSD = thongTinSDAdminService.getThongTinSDByMaTB(maThietBi);
        model.addAttribute("thongTinSD",thongTinSD);
        return "admin/trathietbiresult";
    }
    
    
   @GetMapping("/admin/trathietbi/resultwithmember")
    public String traThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("maSoSinhVien") int maSoSinhVien, Model model) {
        ThongTinSD thongTinSD = thongTinSDAdminService.getThongTinSDByMaTB(maThietBi);
        model.addAttribute("thongTinSD",thongTinSD);
        
        List<String> messages = new ArrayList<>();
        boolean exists=true;
        if(maSoSinhVien != thongTinSD.getThanhVien().getMaTV()){
            exists=true;
            String message = "Mã số sinh viên " + maSoSinhVien + " không trùng với mã sinh viên đã mượn!";
            messages.add(message);
            model.addAttribute("maSoSinhVien", maSoSinhVien);
            model.addAttribute("coViPham", exists);
            model.addAttribute("messages", messages);
        }else{
            exists = false;
            String message = "Mã số sinh viên " + maSoSinhVien + " trả thiết bị thành công!";
            LocalDateTime timeNow = LocalDateTime.now();
            thongTinSD.setTgTra(timeNow);
            thongTinSDAdminService.updateThongTinSD(thongTinSD);
            
            model.addAttribute("coViPham", exists);
            model.addAttribute("messages", messages);
            model.addAttribute("maSoSinhVien", maSoSinhVien);
        }
        return "admin/trathietbiresultwithmember";
    }
    
    
    @GetMapping("/admin/datchomuonthietbi")
    public String datChoMuonThietBi (Model model){
        List<ThietBiDTO> thietBis = thietBiAdminService.getThietBiNotInThongTinSDs();
        model.addAttribute("thietBis", thietBis);
        List<ThongTinSDDTO> thongTinSDDTOs = thongTinSDAdminService.getThongTinSDDaDatCho();
        model.addAttribute("thongTinSDDTOs", thongTinSDDTOs);
        return "admin/datchomuonthietbi";
    }
    
    
    @GetMapping("/admin/datchomuonthietbi/result")
    public String datChoMuonThietBiResult(@RequestParam("maThietBi") int maThietBi, Model model) {
        ThietBiDTO thietBiDTO = thietBiAdminService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi",thietBiDTO);

        return "admin/datchomuonthietbiresult";
    }
    
    
    @GetMapping("/admin/datchomuonthietbi/resultwithmember")
    public String datChoMuonThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("maSoSinhVien") int maSoSinhVien, Model model, @RequestHeader(value = "referer", required = false) String referer) {
        ThietBiDTO thietBiDTO = thietBiAdminService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi",thietBiDTO);
        ThanhVienDTO thanhVienDTO = thanhVienAdminService.getThanhVienDTOById(maSoSinhVien);
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
            List<XuLy> xuLys = xuLyAdminService.getXuLyByMaTV(maSoSinhVien);
            if (xuLys != null && !xuLys.isEmpty()) {
                exists = xuLyAdminService.checkMaSoSVTrongXuLy(maSoSinhVien);
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
                ThanhVien thanhVien = thanhVienAdminService.getThanhVienById(maSoSinhVien);
                LocalDateTime timeNow = LocalDateTime.now();
                ThietBi thietBi = thietBiAdminService.getThietBiByID(maThietBi);
                ThongTinSDDTO thongTinSDDTO = new ThongTinSDDTO(thanhVien, thietBi, null, null, null, timeNow);
                thongTinSDAdminService.addThongTinSD(thongTinSDDTO);
                String message = "Đặt chổ mượn thiết bị thành công!";
                messages.add(message);
                model.addAttribute("messages", messages);
            }
            model.addAttribute("maSoSinhVien", maSoSinhVien);
        }
        
        return "admin/datchomuonthietbiresultwithmember";

    }
    
    @GetMapping("/admin/datchomuonthietbi-laythietbi/result")
    public String datChoMuonThietBiLayThietBi(@RequestParam("maThietBi") int maThietBi, Model model, @RequestHeader(value = "referer", required = false) String referer) {
        ThietBiDTO thietBiDTO = thietBiAdminService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi",thietBiDTO);
        return "admin/datchomuonthietbi_laythietbiresult";
    }
    
    
    @GetMapping("/admin/datchomuonthietbi-laythietbi/resultwithmember")
    public String datchomuonThietBiLayThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("maSoSinhVien") int maSoSinhVien, Model model) {
        ThongTinSD thongTinSD = thongTinSDAdminService.getThongTinSDByMaTB(maThietBi);
        model.addAttribute("thongTinSD",thongTinSD);
        
        List<String> messages = new ArrayList<>();
        boolean exists=true;
        if(maSoSinhVien != thongTinSD.getThanhVien().getMaTV()){
            exists=true;
            String message = "Mã số sinh viên " + maSoSinhVien + " không trùng với mã sinh viên đã mượn!";
            messages.add(message);
            model.addAttribute("maSoSinhVien", maSoSinhVien);
            model.addAttribute("coViPham", exists);
            model.addAttribute("messages", messages);
        }else{
            exists = false;
            String message = "Mã số sinh viên " + maSoSinhVien + " trả thiết bị thành công!";
            LocalDateTime timeNow = LocalDateTime.now();
            thongTinSD.setTgMuon(timeNow);
            thongTinSDAdminService.updateThongTinSD(thongTinSD);
            
            model.addAttribute("coViPham", exists);
            model.addAttribute("messages", messages);
            model.addAttribute("maSoSinhVien", maSoSinhVien);
        }
        return "admin/datchomuonthietbi_laythietbiresultwithmember";
    }
    
    
}
