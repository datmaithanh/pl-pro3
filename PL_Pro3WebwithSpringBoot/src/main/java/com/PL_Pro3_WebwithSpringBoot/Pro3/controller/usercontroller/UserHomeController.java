package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThanhVienUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThietBiUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThongTinSDUserService;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.XuLyUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserHomeController {

    private final ThanhVienUserService tvService;
    private final XuLyUserService xlService;
    private final ThongTinSDUserService ttService;
    private final ThietBiUserService tbService;

    @Autowired
    public UserHomeController(ThanhVienUserService tvService, XuLyUserService xlService, ThongTinSDUserService ttService, ThietBiUserService tbService) {
        this.tvService = tvService;
        this.xlService = xlService;
        this.ttService = ttService;
        this.tbService = tbService;
    }

    @GetMapping("/user/{maTV}") 
    public String trangUser(HttpSession session, Model model) {
        ThanhVien thanhVien = (ThanhVien) session.getAttribute("thanhVien");
        model.addAttribute("thanhVien", thanhVien);
        return "user/index"; 
    }

    @GetMapping("/user/hoso")
    public String trangHoSo(HttpSession session, Model model) {
        ThanhVien thanhVien = (ThanhVien) session.getAttribute("thanhVien");
        model.addAttribute("thanhVien", thanhVien);
        return "user/hoso";
    }

    @GetMapping("/user/vipham")
    public String trangthaivppage(HttpSession session, Model model) {
        ThanhVien thanhVien = (ThanhVien) session.getAttribute("thanhVien");
        List<XuLy> user = xlService.findByThanhVien(thanhVien);

        if (!user.isEmpty()) {
            XuLy xl = user.get(0);

            // Kiểm tra xem thuộc tính soTien có null không trước khi truy cập
            if (xl.getSoTien() != null) {
                model.addAttribute("xuLy", xl);
                model.addAttribute("maTV", thanhVien.getMaTV());
                return "user/xemtrangthaivp";
            } else {
                // Xử lý trường hợp soTien là null ở đây
                xl.setSoTien(0); // Đặt giá trị mặc định cho soTien
                model.addAttribute("xuLy", xl);
                model.addAttribute("maTV", thanhVien.getMaTV());

                return "user/xemtrangthaivp";
            }
        } else {
            model.addAttribute("maTV", thanhVien.getMaTV());
            return "user/khongcothongtin";
        }
    }

    @GetMapping("/user/thietbidangmuon")
    public String trangThietBiDangMuon(HttpSession session, Model model) {
        ThanhVien thanhVien = (ThanhVien) session.getAttribute("thanhVien");
        
        // Tìm thông tin về việc sử dụng thiết bị của thành viên
        Optional<ThongTinSD> user = ttService.findByThanhVien(thanhVien);

        // Tìm thông tin về việc thiết bị đã được sử dụng bởi thành viên
//        List<ThongTinSD> device = thongTinSDRepository.findByThietBi(tb);
        // Kiểm tra xem có thông tin sử dụng thiết bị của thành viên hay không
        if (!user.isEmpty()) {
            ThongTinSD ttsdUser = user.get();
            if (ttsdUser.getThietBi() != null) {
                // Thêm thông tin sử dụng thiết bị của thành viên vào model
                model.addAttribute("thongTin", ttsdUser);
            } else {
                return "user/khongcothongtin";
            }

        } else {
            return "user/khongcothongtin";
        }
        return "user/xemthietbidangmuon";

//        // Kiểm tra xem có thông tin về thiết bị đã được sử dụng bởi thành viên hay không
//        if (!device.isEmpty()) {
//            ThongTinSD ttsdDevice = device.get(0);
//
//            // Thêm thông tin thiết bị đã được sử dụng bởi thành viên vào model
//            model.addAttribute("thongTinSDDevice", ttsdDevice);
//        } 
    }

    @GetMapping("/user/datchothietbi")
    public String datchotbpage(Model model) {
        // Lấy danh sách tất cả các thiết bị từ cơ sở dữ liệu
        List<ThietBi> danhSachThietBi = tbService.findAll();
        // Truyền danh sách các thiết bị qua model
        model.addAttribute("thietBi", danhSachThietBi);
        return "user/xemdatchothietbi";
    }
    
    @GetMapping("/user/doimatkhau")
    public String trangDoiMatKhau(HttpSession session, Model model) {
        ThanhVien thanhVien = (ThanhVien) session.getAttribute("thanhVien");
        model.addAttribute("thanhVien", thanhVien);
        return "user/doimatkhau";
    }
    
    @PostMapping("/user/doimatkhau")
    public String doiMatKhau(
            @RequestParam(name="matKhauCu") String matKhauCu, 
            @RequestParam(name="matKhauMoi") String matKhauMoi, 
            @RequestParam(name="xacNhanMatKhauMoi") String xacNhanMatKhauMoi, 
            HttpSession session, Model model){
        
        ThanhVien thanhVien = (ThanhVien) session.getAttribute("thanhVien");
        
        if(!matKhauCu.equals(thanhVien.getPassword())) {
            model.addAttribute("error", "Mật khẩu hiện tại không chính xác!");
        } else {
            thanhVien.setPassword(matKhauMoi);
            tvService.addOrUpdateThanhVien(thanhVien);
            model.addAttribute("message", "Đổi mật khẩu thành công.");
        }
        return "user/doimatkhau";
    }
}
