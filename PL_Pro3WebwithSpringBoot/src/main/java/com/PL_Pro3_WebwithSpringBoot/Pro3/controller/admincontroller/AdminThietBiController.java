/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThietBiAdminService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lenovo
 */
@Controller
public class AdminThietBiController {
    private ThietBiAdminService thietBiAdminService;
    
    @Autowired
    public AdminThietBiController(ThietBiAdminService thietBiAdminService){
        this.thietBiAdminService = thietBiAdminService;
    }
    
    @GetMapping("/admin/thietbi") // Xử lý yêu cầu GET trên đường dẫn "/admin/thietbi"
    public String Thietbi(Model model) {
        List<ThietBiDTO> list = thietBiAdminService.getAllThietBi();
        model.addAttribute("list", list);
        return "admin/thietbi";
    }  
    
     @GetMapping("/admin/adddevice") // Xử lý yêu cầu GET trên đường dẫn "/admin/adddevice"
    public String addThietbipage(Model model) {
        List<ThietBiDTO> list = thietBiAdminService.getAllThietBi();
        model.addAttribute("list", list);
        return "admin/adddevice";
    }
    
     @GetMapping("/admin/exportexcel") // Xử lý yêu cầu GET trên đường dẫn "/admin/exportexcel"
    public String ExcelThietbipage(Model model) {
        List<ThietBiDTO> list = thietBiAdminService.getAllThietBi();
        model.addAttribute("list", list);
        return "admin/exportexcel";
    }
    
     @GetMapping("/admin/editdevice") // Xử lý yêu cầu GET trên đường dẫn "/admin/editdevice"
    public String EditThietbipage(Model model) {
        List<ThietBiDTO> list = thietBiAdminService.getAllThietBi();
        model.addAttribute("list", list);
        return "admin/editdevice";
    }
    
    
    @GetMapping("/admin/deletedevice") // Xử lý yêu cầu GET trên đường dẫn "/admin/deletedevice"
    public String DeleteThietbipage(Model model) {
        List<ThietBiDTO> list = thietBiAdminService.getAllThietBi();
        model.addAttribute("list", list);
        return "admin/deletedevice";
    }
   
    @GetMapping("/admin/exportexcel_result")// Xử lý yêu cầu get trên đường dẫn "/admin/exportexcel_result"
    public String exportExcelFile(@RequestParam("file") MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            List<ThietBiDTO> thietBis = new ArrayList<>();
            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
            // Bỏ qua dòng tiêu đề nếu cần
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                ThietBiDTO thietBi= new ThietBiDTO();
                thietBi.setMaTB((int) row.getCell(0).getNumericCellValue());
                thietBi.setTenTB(row.getCell(1).getStringCellValue());
                thietBi.setMoTaTB(row.getCell(2).getStringCellValue());                
                thietBis.add(thietBi);
            }
            // Lưu dữ liệu vào cơ sở dữ liệu
            for (ThietBiDTO thietBi: thietBis) {
                thietBiAdminService.AddThietBi(thietBi);
            }

            // Redirect về trang danh sách thành viên sau khi tải lên thành công
            return "redirect:/admin/thietbi";
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi tải lên
        }
        // Redirect về trang upload với thông báo lỗi (nếu cần)
        return "redirect:/admin/exportexcel";
    }
    
    @GetMapping("/admin/editdevice_result")
    public String editThietBi(@RequestParam("maTB") int maTB, @ModelAttribute ThietBiDTO thietBiDTO) {
        thietBiAdminService.updateThietBi(maTB, thietBiDTO);
        return "redirect:/admin/editdevice";
    }
     @GetMapping("/admin/deletedevice_result")
    public String deleteThietBi(@RequestParam("maTB") int maTB) {
        thietBiAdminService.deleteThietBiByID(maTB);
        return "redirect:/admin/deletedevice";
    }
    
}
