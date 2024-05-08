/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThietBiDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThietBiAdminService;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping("/admin/adddevice_result")
    public String addThietBi(@ModelAttribute ThietBiDTO thietBiDTO, Model model) {
        int maTB = thietBiDTO.getMaTB();
        
        boolean isExistingmaTB = thietBiAdminService.isMaTBExisting(maTB);
       
        if (isExistingmaTB) {
            model.addAttribute("coViPham", true);
            model.addAttribute("messages", Collections.singletonList("Thiết Bị" +" "+ maTB + ""+ "đã tồn tại."));
        }  else {
            thietBiAdminService.AddThietBi(thietBiDTO);
            model.addAttribute("coViPham", false);
            model.addAttribute("messages", Collections.singletonList("Thêm thiết bị"+" " + maTB + " "+"thành công."));
        }
        return "/admin/adddevice_result";
    }
    
   
    @PostMapping("/admin/exportexcel_result")// Xử lý yêu cầu get trên đường dẫn "/admin/exportexcel_result"
    public String exportExcelFile(@RequestParam("file") MultipartFile file, Model model) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            List<ThietBiDTO> thietBis = new ArrayList<>();
            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
            boolean Null = false;
            boolean Exist = false;
            
            // Bỏ qua dòng tiêu đề nếu cần
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                ThietBiDTO thietBi= new ThietBiDTO();
                

                // Kiểm tra ô trống
                    if (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK ||
                        row.getCell(1) == null || row.getCell(1).getCellType() == CellType.BLANK ||
                        row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK )
                         {
                        Null = true;
                        break; // Ngừng thêm dữ liệu nếu có ô trống
                    }
                
                int maTB = 0;
                Cell maTBCells = row.getCell(0);
                if (maTBCells != null) {
                    if (maTBCells.getCellType() == CellType.NUMERIC) {
                        maTB = (int) maTBCells.getNumericCellValue();
                    } else if (maTBCells.getCellType() == CellType.STRING) {
                        String maTBValue = maTBCells.getStringCellValue();
                        if (!maTBValue.isEmpty() && maTBValue.matches("\\d+")) {
                            maTB = Integer.parseInt(maTBValue);
                        } else {
                            // Xử lý trường hợp giá trị của ô không phải số nguyên
                        }
                    } else {
                        // Xử lý trường hợp ô không phải kiểu dữ liệu số hoặc chuỗi
                    }
                }
                

                if (thietBiAdminService.isMaTBExisting(maTB)) {
                    Exist = true;
                    break; // Ngừng thêm dữ liệu nếu có trùng matb
                }

                Cell maTBCell = row.getCell(0);
                if (maTBCell.getCellType() == CellType.NUMERIC) {
                    thietBi.setMaTB((int) maTBCell.getNumericCellValue());
                } else if (maTBCell.getCellType() == CellType.STRING) {
                        int maTBcell = Integer.parseInt(maTBCell.getStringCellValue());
                        thietBi.setMaTB(maTBcell);
                }
                
                thietBi.setTenTB(row.getCell(1).getStringCellValue());
                thietBi.setMoTaTB(row.getCell(2).getStringCellValue());               
                
                
              
                
                thietBis.add(thietBi);
            }
            // Lưu dữ liệu vào cơ sở dữ liệu
            for (ThietBiDTO thietBi : thietBis) {
                thietBiAdminService.AddThietBi(thietBi);
            }

            // Redirect về trang danh sách thành viên sau khi tải lên thành công
            if(Null){
                model.addAttribute("coViPham", true);
                model.addAttribute("messages", Collections.singletonList("File excel tồn tại ô trống. Hãy kiểm tra lại"));
            }
            if(Exist){
                model.addAttribute("coViPham", true);
                model.addAttribute("messages", Collections.singletonList("File excel có thiết bị đã tồn tại"));
            }
            return "admin/exportexcel_result";
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi tải lên
        }
        return "redirect:/admin/exportexcel";
    }
    
    @GetMapping("/admin/editdevice_result")
    public String editThietBi(@RequestParam("maTB") int maTB, @ModelAttribute ThietBiDTO thietBiDTO, Model model) {
            boolean success = thietBiAdminService.updateThietBi(maTB, thietBiDTO);
            if(success){
                model.addAttribute("coViPham", false);
            }else{
                model.addAttribute("coViPham", true);
                model.addAttribute("messages", Collections.singletonList("Mã thiết bị không được phép thay đổi"));
            }
            return "/admin/editdevice_result";
    }
     @GetMapping("/admin/deletedevice_result")
    public String deleteThietBi(@RequestParam("maTB") int maTB, Model model) {
        boolean success = thietBiAdminService.deleteThietBiByID(maTB);
        if(success){
            model.addAttribute("coViPham", false);
        }else{
            model.addAttribute("coViPham", true);
        }
        return "/admin/deletedevice_result";
    }    
    
}
