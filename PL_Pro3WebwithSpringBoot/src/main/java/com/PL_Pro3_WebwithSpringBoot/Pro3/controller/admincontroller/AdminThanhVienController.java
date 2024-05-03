/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;
import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThanhVienAdminService;
import io.micrometer.core.instrument.MultiGauge.Row;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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


@Controller

public class AdminThanhVienController {
    
    private ThanhVienAdminService thanhVienAdminService;
    private ThanhVienRepository thanhVienRepository;
    

    @Autowired
    public AdminThanhVienController(ThanhVienAdminService thanhVienAdminService, ThanhVienRepository thanhVienRepository) {
        this.thanhVienAdminService = thanhVienAdminService;
        this.thanhVienRepository = thanhVienRepository;
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
    
    
    
    @GetMapping("/admin/addmember_result") // Xử lý yêu cầu get trên đường dẫn "/admin/addmember_result"
    public String addThanhVien(@ModelAttribute ThanhVienDTO thanhVienDTO, Model model) {
        thanhVienAdminService.AddThanhVien(thanhVienDTO);
        // Optional: Redirect to confirmation page or display success message
        return "redirect:/admin/thanhvien";
    }
    
    @GetMapping("/admin/uploadexcel_result")// Xử lý yêu cầu get trên đường dẫn "/admin/uploadexcel_result"
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            List<ThanhVienDTO> thanhViens = new ArrayList<>();
            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
            // Bỏ qua dòng tiêu đề nếu cần
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                ThanhVienDTO thanhVien = new ThanhVienDTO();
                thanhVien.setMaTV((int) row.getCell(0).getNumericCellValue());
                thanhVien.setHoTen(row.getCell(1).getStringCellValue());
                thanhVien.setKhoa(row.getCell(2).getStringCellValue());
                thanhVien.setNganh(row.getCell(3).getStringCellValue());
                thanhVien.setSdt(row.getCell(4).getStringCellValue());
                thanhVien.setEmail(row.getCell(5).getStringCellValue());
                thanhVien.setPassword(row.getCell(5).getStringCellValue());
                thanhViens.add(thanhVien);
            }
            // Lưu dữ liệu vào cơ sở dữ liệu
            for (ThanhVienDTO thanhVien : thanhViens) {
                thanhVienAdminService.AddThanhVien(thanhVien);
            }

            // Redirect về trang danh sách thành viên sau khi tải lên thành công
            return "redirect:/admin/thanhvien";
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi tải lên
        }
        // Redirect về trang upload với thông báo lỗi (nếu cần)
        return "redirect:/admin/uploadexcel";
    }
    
    @GetMapping("/admin/editmember_result")
    public String editThanhVien(@RequestParam("maTV") int maTV, @ModelAttribute ThanhVienDTO thanhVienDTO) {
        thanhVienAdminService.updateThanhVien(maTV, thanhVienDTO);
        return "redirect:/admin/thanhvien";
    }
    
    @GetMapping("/admin/deletemember_result")
    public String deleteThanhVien(@RequestParam("maTV") int maTV) {
        thanhVienAdminService.deleteThanhVienByID(maTV);
        return "redirect:/admin/thanhvien";
    }
}
