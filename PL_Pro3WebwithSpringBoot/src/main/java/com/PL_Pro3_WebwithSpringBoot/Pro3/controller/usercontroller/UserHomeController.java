package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller;

import static com.PL_Pro3_WebwithSpringBoot.Pro3.controller.usercontroller.UserLoginController.userLogin;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThietBi;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.XuLy;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.XuLyRepository;
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

    @GetMapping("/user")
    public String homepage() {
        return "user/index";
    }

    @GetMapping("/userHoSo")
    public String hosopage(Model model, @RequestParam("maTV") String maTV) {
        // Lấy thông tin thành viên từ session
        Optional<ThanhVien> user = tvService.findById(Integer.parseInt(maTV));
        ThanhVien tv = user.get();
        // Nếu tìm thấy, truyền thông tin thành viên qua model
        if (user != null) {
            model.addAttribute("thanhVien", tv);
        }
        System.out.println("userLogin: " + userLogin.getMaTV());

        model.addAttribute("maTV", userLogin.getMaTV());
        return "user/hoso";
    }

    @GetMapping("/userTrangThaiVP")
    public String trangthaivppage(Model model, @RequestParam("maTV") String maTV) {
        ThanhVien tv = new ThanhVien();
        tv.setMaTV(Integer.parseInt(maTV));
        List<XuLy> user = xlService.findByThanhVien(tv);

        if (!user.isEmpty()) {
            XuLy xl = user.get(0);

            // Kiểm tra xem thuộc tính soTien có null không trước khi truy cập
            if (xl.getSoTien() != null) {
                model.addAttribute("xuLy", xl);
                System.out.println("userLogin: " + userLogin.getMaTV());

                model.addAttribute("maTV", userLogin.getMaTV());
                return "user/xemtrangthaivp";
            } else {
                // Xử lý trường hợp soTien là null ở đây
                xl.setSoTien(0); // Đặt giá trị mặc định cho soTien
                model.addAttribute("xuLy", xl);
                System.out.println("userLogin: " + userLogin.getMaTV());

                model.addAttribute("maTV", userLogin.getMaTV());

                return "user/xemtrangthaivp";
            }
        } else {
            return "user/khongcothongtin";
        }
    }

    @GetMapping("/userThietBiDangMuon")
    public String tbdangmuonpage(Model model, @RequestParam("maTV") String maTV) {
        // Tạo đối tượng ThanhVien và Thiết bị
        ThanhVien tv = new ThanhVien();
//        ThietBi tb = new ThietBi();
        tv.setMaTV(Integer.parseInt(maTV));
//        tb.getMaTB();

        // Tìm thông tin về việc sử dụng thiết bị của thành viên
        Optional<ThongTinSD> user = ttService.findByThanhVien(tv);

        // Tìm thông tin về việc thiết bị đã được sử dụng bởi thành viên
//        List<ThongTinSD> device = thongTinSDRepository.findByThietBi(tb);
        // Kiểm tra xem có thông tin sử dụng thiết bị của thành viên hay không
        if (!user.isEmpty()) {
            ThongTinSD ttsdUser = user.get();
            if (ttsdUser.getThietBi() != null) {
                // Thêm thông tin sử dụng thiết bị của thành viên vào model
                model.addAttribute("maTV", userLogin.getMaTV());
                model.addAttribute("thongTin", ttsdUser);
            } else {
                return "user/khongcothongtin";
            }

        } else {
            model.addAttribute("maTV", userLogin.getMaTV());
            // Trả về trang thông báo không có thông tin nếu không tìm thấy
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

    @GetMapping("/userDatChoThietBi")
    public String datchotbpage(Model model) {
        // Lấy danh sách tất cả các thiết bị từ cơ sở dữ liệu
        List<ThietBi> danhSachThietBi = tbService.findAll();

        // Truyền danh sách các thiết bị qua model
        model.addAttribute("maTV", userLogin.getMaTV());

        model.addAttribute("thietBi", danhSachThietBi);

        return "user/xemdatchothietbi";
    }

}
