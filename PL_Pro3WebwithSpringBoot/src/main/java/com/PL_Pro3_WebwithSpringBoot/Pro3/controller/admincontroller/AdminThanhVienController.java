/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Math.log;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;


@Controller

public class AdminThanhVienController {
    private ThanhVienAdminService thanhVienAdminService;
    private XuLyAdminService xuLyAdminService;
    private ThongTinSDAdminService thongTinSDAdminService;
    private ThietBiAdminService thietBiAdminService;


    @Autowired
    public AdminThanhVienController(ThanhVienAdminService thanhVienAdminService, ThanhVienRepository thanhVienRepository, XuLyAdminService xuLyAdminService, ThongTinSDAdminService thongTinSDAdminService, ThietBiAdminService thietBiAdminService) {
        this.thanhVienAdminService = thanhVienAdminService;
        this.xuLyAdminService = xuLyAdminService;
        this.thongTinSDAdminService = thongTinSDAdminService;
        this.thietBiAdminService = thietBiAdminService;
    }
    
    

    @GetMapping("/admin/thanhvien") // Xử lý yêu cầu GET trên đường dẫn "/admin/thanhvien"
    public String Thanhvien(Model model) {
        List<ThanhVienDTO> list = thanhVienAdminService.getAllThanhVien();
        model.addAttribute("list", list);
        return "admin/thanhvien";
    }
    
    @GetMapping("/admin/addmember") // Xử lý yêu cầu GET trên đường dẫn "/admin/addmember"
    public String addThanhvienpage(Model model) {
        List<ThanhVienDTO> list = thanhVienAdminService.getAllThanhVien();
        model.addAttribute("list", list);
        return "admin/addmember";
    }
    @GetMapping("/admin/uploadexcel") // Xử lý yêu cầu GET trên đường dẫn "/admin/uploadexcel"
    public String ExcelThanhvienpage(Model model) {
        List<ThanhVienDTO> list = thanhVienAdminService.getAllThanhVien();
        model.addAttribute("list", list);
        return "admin/uploadexcel";
    }
    @GetMapping("/admin/editmember") // Xử lý yêu cầu GET trên đường dẫn "/admin/editmember"
    public String EditThanhvienpage(Model model) {
        List<ThanhVienDTO> list = thanhVienAdminService.getAllThanhVien();
        model.addAttribute("list", list);
        return "admin/editmember";
    }
    @GetMapping("/admin/deletemember") // Xử lý yêu cầu GET trên đường dẫn "/admin/deletemember"
    public String DeleteThanhvienpage(Model model) {
        List<ThanhVienDTO> list = thanhVienAdminService.getAllThanhVien();
        model.addAttribute("list", list);
        return "admin/deletemember";
    }
    
    
    
    @GetMapping("/admin/addmember_result")
    public String addThanhVien(@ModelAttribute ThanhVienDTO thanhVienDTO, Model model) {
        int maTV = thanhVienDTO.getMaTV();
        String sdt = thanhVienDTO.getSdt();
        String email = thanhVienDTO.getEmail();

        boolean isExistingmaTV = thanhVienAdminService.isMaTVExisting(maTV);
        boolean isExistingEmail = thanhVienAdminService.isExistingEmail(email);
        boolean isExistingSDT = thanhVienAdminService.isExistingSDT(sdt);
        
        if (isExistingmaTV) {
            model.addAttribute("coViPham", true);
            model.addAttribute("messages", Collections.singletonList("Thành viên" +" "+ maTV + " "+ "đã tồn tại."));
        } else if (isExistingEmail) {
            model.addAttribute("coViPham", true);
            model.addAttribute("messages", Collections.singletonList("Email"+" "+email+ " " +"đã tồn tại."));
        } else if (isExistingSDT) {
            model.addAttribute("coViPham", true);
            model.addAttribute("messages", Collections.singletonList("Số điện thoại"+" "+sdt+" "+"đã tồn tại."));
        } else {
            thanhVienAdminService.AddThanhVien(thanhVienDTO);
            model.addAttribute("coViPham", false);
            model.addAttribute("messages", Collections.singletonList("Thêm thành viên"+" " + maTV + " "+"thành công."));
        }
        return "/admin/addmember_result";
    }
    
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
    
    @PostMapping("/admin/uploadexcel_result")// Xử lý yêu cầu get trên đường dẫn "/admin/uploadexcel_result"
    public String uploadExcelFile(@RequestParam("file") MultipartFile file, Model model) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            List<ThanhVienDTO> thanhViens = new ArrayList<>();
            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
            boolean Null = false;
            boolean Exist = false;
            
            // Bỏ qua dòng tiêu đề nếu cần
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                ThanhVienDTO thanhVien = new ThanhVienDTO();
                

                // Kiểm tra ô trống
                    if (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK ||
                        row.getCell(1) == null || row.getCell(1).getCellType() == CellType.BLANK ||
                        row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK ||
                        row.getCell(3) == null || row.getCell(3).getCellType() == CellType.BLANK ||
                        row.getCell(4) == null || row.getCell(4).getCellType() == CellType.BLANK ||
                        row.getCell(5) == null || row.getCell(5).getCellType() == CellType.BLANK ||
                        row.getCell(6) == null || row.getCell(6).getCellType() == CellType.BLANK) {
                        Null = true;
                        break; // Ngừng thêm dữ liệu nếu có ô trống
                    }
                
                int maTV = 0;
                Cell maTVCells = row.getCell(0);
                if (maTVCells != null) {
                    if (maTVCells.getCellType() == CellType.NUMERIC) {
                        maTV = (int) maTVCells.getNumericCellValue();
                    } else if (maTVCells.getCellType() == CellType.STRING) {
                        String maTVValue = maTVCells.getStringCellValue();
                        if (!maTVValue.isEmpty() && maTVValue.matches("\\d+")) {
                            maTV = Integer.parseInt(maTVValue);
                        } else {
                            // Xử lý trường hợp giá trị của ô không phải số nguyên
                        }
                    } else {
                        // Xử lý trường hợp ô không phải kiểu dữ liệu số hoặc chuỗi
                    }
                }
                String email = row.getCell(6).getStringCellValue();
                String sdt = row.getCell(4).getStringCellValue();

                if (thanhVienAdminService.isMaTVExisting(maTV) || thanhVienAdminService.isExistingEmail(email) || thanhVienAdminService.isExistingSDT(sdt)) {
                    Exist = true;
                    break; // Ngừng thêm dữ liệu nếu có trùng matv, email hoặc sdt
                }

                Cell maTVCell = row.getCell(0);
                if (maTVCell.getCellType() == CellType.NUMERIC) {
                    thanhVien.setMaTV((int) maTVCell.getNumericCellValue());
                } else if (maTVCell.getCellType() == CellType.STRING) {
                        int maTVcell = Integer.parseInt(maTVCell.getStringCellValue());
                        thanhVien.setMaTV(maTVcell);
                }
                
                thanhVien.setHoTen(row.getCell(1).getStringCellValue());
                thanhVien.setKhoa(row.getCell(2).getStringCellValue());
                thanhVien.setNganh(row.getCell(3).getStringCellValue());
                thanhVien.setSdt(row.getCell(4).getStringCellValue());
                
                //Kiểm tra xem cột email có kí tự '@' hay kh bằng regex
                Cell emailCell = row.getCell(6);
                if (emailCell.getCellType() == CellType.STRING) {
                    email = emailCell.getStringCellValue();

                    // Kiểm tra tính hợp lệ của email
                    if (isValidEmail(email)) {
                        thanhVien.setEmail(email);
                    }
                   
                }
                
                //Check cột mật khẩu, nếu pw trong excel là số thì chuyển sang string và bỏ số thập
                //phân, còn nếu là số kết hợp với chữ hoặc toàn là chữ là thêm vào database
                DecimalFormat decimalFormat = new DecimalFormat("#.################");
                Cell passwordCell = row.getCell(5);
                if (passwordCell.getCellType() == CellType.NUMERIC) {
                    double passwordValue = passwordCell.getNumericCellValue();

                    // Format giá trị số và loại bỏ phần thập phân 0 và dấu ".0"
                    String passwordString = decimalFormat.format(passwordValue);
                    if (passwordString.endsWith(".0")) {
                        passwordString = passwordString.substring(0, passwordString.length() - 2);
                    }
                    thanhVien.setPassword(passwordString);
                } else if (passwordCell.getCellType() == CellType.STRING) {
                    // Nếu ô có định dạng chuỗi, lấy giá trị trực tiếp
                    thanhVien.setPassword(passwordCell.getStringCellValue());
                }
                
                thanhViens.add(thanhVien);
            }
            // Lưu dữ liệu vào cơ sở dữ liệu
            for (ThanhVienDTO thanhVien : thanhViens) {
                thanhVienAdminService.AddThanhVien(thanhVien);
            }

            // Redirect về trang danh sách thành viên sau khi tải lên thành công
            if(Null){
                model.addAttribute("coViPham", true);
                model.addAttribute("messages", Collections.singletonList("File excel tồn tại ô trống. Hãy kiểm tra lại"));
            }
            if(Exist){
                model.addAttribute("coViPham", true);
                model.addAttribute("messages", Collections.singletonList("File excel có thành viên đã tồn tại"));
            }
            return "admin/uploadexcel_result";
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi tải lên
        }
        return "redirect:/admin/uploadexcel";
    }
    
    @GetMapping("/admin/editmember_result")
    public String editThanhVien(@RequestParam("maTV") int maTV, @ModelAttribute ThanhVienDTO thanhVienDTO, Model model) {
            boolean success = thanhVienAdminService.updateThanhVien(maTV, thanhVienDTO);
            if(success){
                model.addAttribute("coViPham", false);
            }else{
                model.addAttribute("coViPham", true);
                model.addAttribute("messages", Collections.singletonList("Mã thành viên không được phép thay đổi"));
            }
            return "/admin/editmember_result";
    }
    
    @GetMapping("/admin/deletemember_result")
    public String deleteThanhVien(@RequestParam("maTV") int maTV, Model model) {
        boolean success = thanhVienAdminService.deleteThanhVienByID(maTV);
        if(success){
            model.addAttribute("coViPham", false);
        }else{
            model.addAttribute("coViPham", true);
        }
        return "/admin/deletemember_result";
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
            List<XuLy> xuLys = xuLyAdminService.findByThanhVienMaTVTrueViPham(maSoSinhVien);
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
            List<XuLy> xuLys = xuLyAdminService.findByThanhVienMaTVTrueViPham(maSoSinhVien);
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
            List<XuLy> xuLys = xuLyAdminService.findByThanhVienMaTVTrueViPham(maSoSinhVien);
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
        List<ThongTinSDDTO> thongTinTraThietBis = thongTinSDAdminService.getThongTinThietbiCanTra();
        model.addAttribute("thongTinTraThietBis", thongTinTraThietBis);
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
    public String datChoMuonThietBiResult(@RequestParam("maThietBi") int maThietBi,
                                           Model model) {       
        LocalTime gioMuon = LocalTime.now().plusMinutes(1);

        String gioMuonFormatted = gioMuon.format(DateTimeFormatter.ofPattern("HH:mm"));

        // Thêm giá trị vào input gioMuon
        model.addAttribute("gioMuon", gioMuonFormatted);
        

        
       
        ThietBiDTO thietBiDTO = thietBiAdminService.getThietBiDTOByID(maThietBi);

       
        model.addAttribute("thietBi", thietBiDTO);
        

        return "admin/datchomuonthietbiresult";
    }

    
    
    @GetMapping("/admin/datchomuonthietbi/resultwithmember")
    public String datChoMuonThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("maSoSinhVien") int maSoSinhVien, @RequestParam("thoiGianMuon") String thoiGianMuon, @RequestParam("gioMuon") String gioMuon, Model model, @RequestHeader(value = "referer", required = false) String referer) {
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
            List<XuLy> xuLys = xuLyAdminService.findByThanhVienMaTVTrueViPham(maSoSinhVien);
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime tgDatMuonFinal = LocalDateTime.parse(thoiGianMuon+"T"+gioMuon, formatter);
                ThietBi thietBi = thietBiAdminService.getThietBiByID(maThietBi);
                ThongTinSDDTO thongTinSDDTO = new ThongTinSDDTO(thanhVien, thietBi, null, null, null, tgDatMuonFinal);
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
    public String datChoMuonThietBiLayThietBi(@RequestParam("maThietBi") int maThietBi, Model model) {
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
    
    
    @GetMapping("/admin/datchomuonthietbi-huylaythietbi/result")
    public String datChoMuonThietBiHuyLayThietBi(@RequestParam("maThietBi") int maThietBi, Model model) {
        ThietBiDTO thietBiDTO = thietBiAdminService.getThietBiDTOByID(maThietBi);
        model.addAttribute("thietBi",thietBiDTO);
        return "admin/datchomuonthietbi_huylaythietbiresult";
    }
    
    @GetMapping("/admin/datchomuonthietbi-huylaythietbi/resultwithmember")
    public String datchomuonThietBiHuyLayThietBiResultCoMaThanhVien(@RequestParam("maThietBi") int maThietBi, @RequestParam("maSoSinhVien") int maSoSinhVien, Model model) {
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
            String message = "Mã số sinh viên " + maSoSinhVien + " hủy mượn thiết bị thành công!";
            thongTinSDAdminService.deleteThongTinSDId(thongTinSD.getMaTT());
            
            model.addAttribute("coViPham", exists);
            model.addAttribute("messages", messages);
            model.addAttribute("maSoSinhVien", maSoSinhVien);
        }
        return "admin/datchomuonthietbi_huylaythietbiresultwithmember";
    }
    
    
}
